package org.namofo.radio.mvp.ui.activity

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.jess.arms.di.component.AppComponent
import com.jess.arms.mvp.IPresenter
import org.namofo.radio.R
import org.namofo.radio.app.ARouterPath
import org.namofo.radio.mvp.ui.base.BaseSupportActivity
import org.namofo.radio.mvp.ui.fragment.MainFragment

/**
 *
 * Copyright:Copyright(c) 2018
 * CreateTime:18/5/2$  09:41$
 * @author 郑炯
 * @version 1.0
 */
@Route(path = ARouterPath.MAIN_PAGE)
class MainActivity : BaseSupportActivity<IPresenter>() {
    override fun setupActivityComponent(appComponent: AppComponent) {

    }

    override fun initData(savedInstanceState: Bundle?) {
        if (findFragment(MainFragment::class.java) == null) {
            loadRootFragment(R.id.rl_container, MainFragment.newInstance())
        }
    }

    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_main
    }

}