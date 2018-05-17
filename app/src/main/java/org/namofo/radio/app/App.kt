package org.namofo.radio.app

import com.blankj.utilcode.util.Utils
import com.jess.arms.base.BaseApplication
import com.lzx.musiclibrary.cache.CacheConfig
import com.lzx.musiclibrary.utils.BaseUtil
import com.lzx.musiclibrary.manager.MusicLibrary



/**
 * Created by zhengjiong
 * date: 2018/1/1 21:56
 */
class App : BaseApplication() {

    companion object {
        @JvmStatic
        lateinit var app: App
    }


    override fun onCreate() {
        super.onCreate()
        app = this@App
        Utils.init(this)
        if (!BaseUtil.getCurProcessName(this).contains(":musicLibrary")) {
            val builder = MusicLibrary.Builder(this)
                    .setAutoPlayNext(false)//是否在播放完当前歌曲后自动播放下一首
                    .setUseMediaPlayer(false)//是否使用 MediaPlayer
                    //.setNotificationCreater(NotificationCreater)//通知栏配置
                    .setCacheConfig(CacheConfig.DEFAULT)//边播边存配置
            val musicLibrary = builder
                    .build()
            musicLibrary.init()
        }
    }


}