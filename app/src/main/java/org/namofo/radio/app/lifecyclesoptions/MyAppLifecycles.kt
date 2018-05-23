package org.namofo.radio.app.lifecyclesoptions

import android.app.Application
import android.content.Context
import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.utilcode.util.Utils
import com.jess.arms.base.delegate.AppLifecycles
import com.lzx.musiclibrary.cache.CacheConfig
import com.lzx.musiclibrary.manager.MusicLibrary
import com.lzx.musiclibrary.utils.BaseUtil
import me.yokeyword.fragmentation.Fragmentation
import net.danlew.android.joda.JodaTimeAndroid
import org.namofo.radio.BuildConfig

/**
 * Created by zhengjiong
 * date: 2018/1/28 17:59
 */
class MyAppLifecycles : AppLifecycles {
    override fun attachBaseContext(base: Context?) {


    }

    override fun onCreate(application: Application?) {
        initARouter(application!!)
        initFragmentation()
        Utils.init(application)
        if (!BaseUtil.getCurProcessName(application).contains(":musicLibrary")) {
            val builder = MusicLibrary.Builder(application)
                    .setAutoPlayNext(false)//是否在播放完当前歌曲后自动播放下一首
                    .setUseMediaPlayer(false)//是否使用 MediaPlayer
                    //.setNotificationCreater(NotificationCreater)//通知栏配置
                    .setCacheConfig(CacheConfig.DEFAULT)//边播边存配置
            val musicLibrary = builder
                    .build()
            musicLibrary.init()
        }
        JodaTimeAndroid.init(application)
    }

    override fun onTerminate(application: Application?) {

    }

    private fun initFragmentation() {
        Fragmentation.builder()
                .stackViewMode(Fragmentation.BUBBLE)
                .debug(BuildConfig.DEBUG)
                .handleException({
                    it.printStackTrace()
                })
                .install()

    }


    private fun initARouter(application: Application) {
        if (BuildConfig.DEBUG) {
            ARouter.openDebug()
            ARouter.openLog()
            ARouter.printStackTrace() // 打印日志的时候打印线程堆栈
        }
        ARouter.init(application)
    }
}