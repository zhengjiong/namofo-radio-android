package org.namofo.radio.mvp.ui.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import org.namofo.radio.R
import org.namofo.radio.app.ARouterPath
import java.util.concurrent.TimeUnit

/**
 *
 * Copyright:Copyright(c) 2017
 * CreateTime:17/12/26  08:46
 * @author zhengjiong
 * @version 1.0
 */
@Route(path = ARouterPath.LAUNCHER)
class LauncherActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_launcher)

        Flowable.just(true)
                .delay(1000L, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    ARouter.getInstance().build(ARouterPath.MAIN_PAGE).navigation(this)
                    startActivity(Intent(this@LauncherActivity, MainActivity::class.java))
                    finish()
                }, {
                })
    }
}
