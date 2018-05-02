package org.namofo.radio.mvp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jess.arms.di.component.AppComponent
import com.jess.arms.mvp.IPresenter
import com.meimeiyoupin.mvp.model.entity.TabEntity
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_main.view.*
import org.namofo.radio.R
import org.namofo.radio.mvp.ui.adapter.MainFragmentPagerAdapter
import org.namofo.radio.mvp.ui.base.BaseSupportFragment

/**
 *
 * Copyright:Copyright(c) 2018
 * CreateTime:18/5/2$  10:16$
 * @author 郑炯
 * @version 1.0
 */
class MainFragment : BaseSupportFragment<IPresenter>() {
    companion object {
        @JvmStatic
        fun newInstance() = MainFragment()
    }

    override fun setupFragmentComponent(appComponent: AppComponent) {

    }

    override fun setData(data: Any?) {

    }

    override fun initView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun initData(savedInstanceState: Bundle?) {
        viewPager.adapter = MainFragmentPagerAdapter(arrayOf("推荐", "直播", "音频"), childFragmentManager)
        tabLayout.setViewPager(viewPager)
    }

}