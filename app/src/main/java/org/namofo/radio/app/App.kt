package org.namofo.radio.app

import com.jess.arms.base.BaseApplication

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