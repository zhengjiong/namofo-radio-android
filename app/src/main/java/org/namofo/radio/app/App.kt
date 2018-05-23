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
    }


}