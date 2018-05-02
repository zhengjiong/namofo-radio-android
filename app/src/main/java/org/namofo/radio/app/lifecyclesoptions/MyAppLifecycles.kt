package org.namofo.radio.app.lifecyclesoptions

import android.app.Application
import android.content.Context
import com.alibaba.android.arouter.launcher.ARouter
import com.jess.arms.base.delegate.AppLifecycles
import me.yokeyword.fragmentation.Fragmentation
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