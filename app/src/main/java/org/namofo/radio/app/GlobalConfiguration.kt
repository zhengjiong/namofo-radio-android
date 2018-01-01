package org.namofo.radio.app

import android.app.Application
import android.content.Context
import android.support.v4.app.FragmentManager
import com.jess.arms.base.delegate.AppLifecycles
import com.jess.arms.di.module.GlobalConfigModule
import com.jess.arms.integration.ConfigModule

/**
 * Created by zhengjiong
 * date: 2018/1/1 21:58
 */
class GlobalConfiguration : ConfigModule {
    override fun injectFragmentLifecycle(context: Context?, lifecycles: MutableList<FragmentManager.FragmentLifecycleCallbacks>?) {
        //向Fragment的生命周期中注入一些自定义逻辑
    }

    override fun applyOptions(context: Context?, builder: GlobalConfigModule.Builder?) {
        /*builder.baseurl(Api.APP_DOMAIN)
                .cacheFile(New File("cache"));*/
    }

    override fun injectAppLifecycle(context: Context?, lifecycles: MutableList<AppLifecycles>?) {
        //向Application的生命周期中注入一些自定义逻辑
    }

    override fun injectActivityLifecycle(context: Context?, lifecycles: MutableList<Application.ActivityLifecycleCallbacks>?) {
        //向Activity的生命周期中注入一些自定义逻辑
    }

}