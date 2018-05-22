package org.namofo.radio.mvp.ui.fragment

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alibaba.android.arouter.facade.annotation.Route
import com.blankj.utilcode.util.ToastUtils
import com.jakewharton.rxbinding2.view.RxView
import com.jess.arms.di.component.AppComponent
import com.jess.arms.mvp.IPresenter
import com.jess.arms.utils.RxLifecycleUtils
import com.lzx.musiclibrary.aidl.listener.OnPlayerEventListener
import com.lzx.musiclibrary.aidl.model.SongInfo
import com.lzx.musiclibrary.manager.MusicManager
import com.lzx.musiclibrary.manager.TimerTaskManager
import kotlinx.android.synthetic.main.fragment_audio_detail.*
import kotlinx.android.synthetic.main.toolbar.*
import org.namofo.radio.R
import org.namofo.radio.app.ARouterPath
import org.namofo.radio.mvp.ui.base.BaseNamofoFragment
import org.namofo.radio.utils.IntentKey
import java.util.concurrent.TimeUnit

/**
 * 播放界面
 *
 * Copyright:Copyright(c) 2018
 * CreateTime:18/5/19$  11:07$
 * @author 郑炯
 * @version 1.0
 */
@Route(path = ARouterPath.AUDIO_DETAIL)
class AudioDetailFragment : BaseNamofoFragment<IPresenter>(), OnPlayerEventListener {

    private val mTimerTaskManager by lazy {
        TimerTaskManager()
    }

    override fun onPlayerStop() {

    }

    override fun onAsyncLoading(isFinishLoading: Boolean) {

    }

    override fun onMusicSwitch(music: SongInfo?) {

    }

    override fun onPlayCompletion() {
        ivPlayer.setImageResource(R.mipmap.ic_bf_n)
        seekBar.progress = 0
        tvStartTime.text = "00:00"

    }

    override fun onPlayerPause() {
        ivPlayer.setImageResource(R.mipmap.ic_bf_n)
        mTimerTaskManager.stopSeekBarUpdate()
    }

    override fun onPlayerStart() {
        ivPlayer.setImageResource(R.mipmap.ic_pause_n)
        mTimerTaskManager.scheduleSeekBarUpdate()
    }

    override fun onError(errorMsg: String?) {
        ivPlayer.setImageResource(R.mipmap.ic_bf_n)
        tvStartTime.text = "00:00"
        seekBar.progress = 0
        ToastUtils.showShort("播放失败")
    }

    private val updateProgress = Runnable {
        val progress = musicManager.progress
        val bufferProgress = musicManager.bufferedPosition

        seekBar.progress = progress.toInt()
        seekBar.secondaryProgress = bufferProgress.toInt()
    }

    private var mSongInfo: SongInfo? = null
    private val musicManager by lazy {
        MusicManager.get()
    }

    companion object {
        @JvmStatic
        fun newInstance(songInfo: SongInfo): AudioDetailFragment {
            return AudioDetailFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(IntentKey.KEY_SONG_INFO, songInfo)
                }
            }
        }
    }

    override fun setupFragmentComponent(appComponent: AppComponent) {

    }

    override fun setData(data: Any?) {

    }

    override fun initView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_audio_detail, container, false)
    }

    override fun initData(savedInstanceState: Bundle?) {
        view?.setBackgroundColor(ContextCompat.getColor(context!!, R.color.white))
        toolbar.title = "媒体名称"
        toolbar.setNavigationOnClickListener {
            pop()
        }

        mSongInfo = arguments?.getParcelable(IntentKey.KEY_SONG_INFO)

        if (mSongInfo == null) {
            errorNoCancel("获取媒体名称出错")
            return
        }
        mTimerTaskManager.setUpdateProgressTask(updateProgress)
        musicManager.addPlayerEventListener(this)

        if (MusicManager.isCurrMusicIsPlaying(mSongInfo)) {
            //当前音乐正在播放, 并且是当前点进来的音乐
            mTimerTaskManager.scheduleSeekBarUpdate()
        } else if (MusicManager.isCurrMusicIsPlayingMusic(mSongInfo)) {
            //是当前音乐, 但是当前音乐已经暂停
            musicManager.resumeMusic()
        } else {
            musicManager.playMusicByInfo(mSongInfo)
        }

        mSongInfo?.apply {
            seekBar.max = duration.toInt()
        }

        if (MusicManager.isPlaying()) {
            ivPlayer.setImageResource(R.mipmap.ic_pause_n)
        } else {
            ivPlayer.setImageResource(R.mipmap.ic_bf_n)
        }
        RxView.clicks(ivPlayer)
                .throttleFirst(300, TimeUnit.MILLISECONDS)
                .compose(RxLifecycleUtils.bindToLifecycle(this))
                .subscribe({
                    /*if (MusicManager.isCurrMusicIsPlaying(mSongInfo)) {
                        ivPlayer.setImageResource(R.mipmap.ic_bf_n)
                    } else {
                        ivPlayer.setImageResource(R.mipmap.ic_pause_n)
                    }*/
                    musicManager.playMusicByInfo(mSongInfo)
                }) {
                }
    }


    override fun onDestroyView() {
        mTimerTaskManager.stopSeekBarUpdate()
        mTimerTaskManager.onRemoveUpdateProgressTask()
        super.onDestroyView()
    }
}
