package org.namofo.radio.app.media

import android.os.PowerManager
import com.blankj.utilcode.util.ToastUtils
import com.lzx.musiclibrary.manager.MusicManager
import com.pili.pldroid.player.*
import org.namofo.radio.app.App
import org.namofo.radio.mvp.model.event.RadioControlEvent
import org.namofo.radio.utils.LogUtils
import org.simple.eventbus.EventBus
import java.io.IOException

/**
 *
 * Copyright:Copyright(c) 2018
 * CreateTime:18/5/2$  17:34$
 * @author 郑炯
 * @version 1.0
 */

object RadioPlayer {
    const val RTMP_PATH = "rtmp://live.hkstv.hk.lxdns.com/live/hks"

    const val RADIO_PLAY_ACTION = "org.namofo.radio.play"
    const val RADIO_STOP_ACTION = "org.namofo.radio.stop"
    const val RADIO_PLAY_OR_STOP_ACTION = "org.namofo.radio.playorstop"

    private var mPLMediaPlayer: PLMediaPlayer? = null
    //private var mIsStopped = false
    private var mIsPrepared = false

    private val mAVOptions: AVOptions by lazy {
        AVOptions().apply {
            // 准备超时时间，包括创建资源、建立连接、请求码流等，单位是 ms
            // 默认值是：无
            setInteger(AVOptions.KEY_PREPARE_TIMEOUT, 10 * 1000)


            // 读取视频流超时时间，单位是 ms
            // 默认值是：10 * 1000
            //setInteger(AVOptions.KEY_GET_AV_FRAME_TIMEOUT, 10 * 1000)


            // 当前播放的是否为在线直播，如果是，则底层会有一些播放优化
            // 默认值是：0:关闭优化, 1:开启优化
            setInteger(AVOptions.KEY_LIVE_STREAMING, 1)


            // 是否开启"延时优化"，只在在线直播流中有效
            // 默认值是：0 : 关闭
            //setInteger(AVOptions.KEY_DELAY_OPTIMIZATION, 1)

            // 解码方式:
            // codec＝AVOptions.MEDIA_CODEC_HW_DECODE，硬解
            // codec=AVOptions.MEDIA_CODEC_SW_DECODE, 软解
            // codec=AVOptions.MEDIA_CODEC_AUTO, 硬解优先，失败后自动切换到软解
            // 默认值是：MEDIA_CODEC_SW_DECODE
            setInteger(AVOptions.KEY_MEDIACODEC, AVOptions.MEDIA_CODEC_AUTO)

            // whether start play automatically after prepared, default value is 1
            // 是否自动启动播放，如果设置为 1，则在调用 `prepareAsync` 或者 `setVideoPath` 之后自动启动播放，无需调用 `start()`
            // 默认值是：1,
            // 0:不自动播放
            //setInteger(AVOptions.KEY_START_ON_PREPARED, 0)


            // 最大的缓存大小，单位是 ms
            // 默认值是：4000
            setInteger(AVOptions.KEY_MAX_CACHE_BUFFER_DURATION, 4000)
        }
    }

    private val onPrepareListener = PLOnPreparedListener { preparedTime ->
        LogUtils.e("preparedTime=$preparedTime")
        EventBus.getDefault().post(RadioControlEvent(RADIO_PLAY_ACTION))
        mPLMediaPlayer?.start()
        //mIsStopped = false
        mIsPrepared = true
    }

    private val onErrorListener = PLOnErrorListener { errorCode ->
        LogUtils.e("PLOnErrorListener onError=$errorCode")
        when (errorCode) {

            PLOnErrorListener.ERROR_CODE_IO_ERROR -> {
                //网络连接失败, SDK将会自动重连, 这里不让重连,直接释放
                ToastUtils.showShort("网络连接失败!")
            }
            PLOnErrorListener.ERROR_CODE_OPEN_FAILED -> {
                //播放器打开失败
                ToastUtils.showShort("播放器打开失败")
            }
            PLOnErrorListener.ERROR_CODE_SEEK_FAILED -> {
                ToastUtils.showShort("解码失败!")
            }
            PLOnErrorListener.ERROR_CODE_PLAYER_CREATE_AUDIO_FAILED -> {
                ToastUtils.showShort("AudioTrack 初始化失败，可能无法播放音频")
            }
            else -> {
                ToastUtils.showShort("未知错误")
            }
        }

        release()
        return@PLOnErrorListener true
    }

    private val onCompleteListener = PLOnCompletionListener {
        LogUtils.e("PLOnCompletionListener onCompletion")
        release()
    }

    private val onBufferingUpdateListener = object : PLOnBufferingUpdateListener {
        override fun onBufferingUpdate(p0: Int) {
            LogUtils.e("onBufferingUpdate=$p0")
        }
    }

    val onInfoListener = PLOnInfoListener { what, extra ->
        //LogUtils.e("PLOnInfoListener onInfo what=$what ,extra=$extra")
        when (what) {
            PLOnInfoListener.MEDIA_INFO_BUFFERING_START -> {
                //开始缓冲
            }
            PLOnInfoListener.MEDIA_INFO_BUFFERING_END -> {
                //结束缓冲
            }
            PLOnInfoListener.MEDIA_INFO_AUDIO_RENDERING_START -> {
                //第一帧音频已成功播放, 消息的 extra 参数附带首帧时间

                //停止录音播放
                MusicManager.get().reset()
            }
        }
    }

    fun playOrStop() {
        if (!mIsPrepared) {
            prepare()
        } else {
            release()
        }
    }

    fun stop() {
        release()
    }

    private fun prepare() {
        if (mPLMediaPlayer == null) {
            mPLMediaPlayer = PLMediaPlayer(App.app, mAVOptions).apply {
                setOnPreparedListener(onPrepareListener)
                setOnErrorListener(onErrorListener)
                setOnCompletionListener(onCompleteListener)
                setOnBufferingUpdateListener(onBufferingUpdateListener)
                setOnInfoListener(onInfoListener)
                setWakeMode(App.app, PowerManager.PARTIAL_WAKE_LOCK)
            }
        }
        try {
            mPLMediaPlayer!!.dataSource = RTMP_PATH
            mPLMediaPlayer!!.prepareAsync()
        } catch (e: IllegalArgumentException) {
            LogUtils.e(e.message)
        } catch (e: IllegalStateException) {
            LogUtils.e(e.message)
        } catch (e: IOException) {
            LogUtils.e(e.message)
        }
    }

    private fun release() {
        EventBus.getDefault().post(RadioControlEvent(RADIO_STOP_ACTION))
        mPLMediaPlayer?.run {
            mPLMediaPlayer?.stop()
            mPLMediaPlayer?.release()
            mPLMediaPlayer = null
            mIsPrepared = false
        }
    }
}