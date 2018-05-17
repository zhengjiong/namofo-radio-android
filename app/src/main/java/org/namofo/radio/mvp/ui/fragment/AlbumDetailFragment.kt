package org.namofo.radio.mvp.ui.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alibaba.android.arouter.facade.annotation.Route
import com.jess.arms.di.component.AppComponent
import com.jess.arms.mvp.IPresenter
import kotlinx.android.synthetic.main.fragment_album_detail.*
import org.namofo.radio.R
import org.namofo.radio.app.ARouterPath
import org.namofo.radio.mvp.ui.adapter.AlbumDetailListAdapter
import org.namofo.radio.mvp.ui.base.BaseSupportFragment

/**
 *
 * Copyright:Copyright(c) 2018
 * CreateTime:18/5/17$  19:51$
 * @author 郑炯
 * @version 1.0
 */

@Route(path = ARouterPath.ALBUM_DETAIL)
class AlbumDetailFragment : BaseSupportFragment<IPresenter>() {

    companion object {
        @JvmStatic
        fun newInstance() = AlbumDetailFragment()
    }

    override fun setupFragmentComponent(appComponent: AppComponent) {

    }

    override fun setData(data: Any?) {

    }

    override fun initView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_album_detail, container, false)
    }

    override fun initData(savedInstanceState: Bundle?) {
        toolbar.setNavigationOnClickListener {
            pop()
        }
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = AlbumDetailListAdapter(mutableListOf("","","","","","","","","","","",""))
    }

}