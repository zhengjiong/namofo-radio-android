package org.namofo.radio.mvp.ui.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jess.arms.di.component.AppComponent
import com.jess.arms.mvp.IPresenter
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration
import kotlinx.android.synthetic.main.recyclerview_layout.*
import org.namofo.radio.R
import org.namofo.radio.mvp.model.entity.RecommendListEntity
import org.namofo.radio.mvp.ui.adapter.RecommendListAdapter
import org.namofo.radio.mvp.ui.base.BaseSupportFragment

/**
 *
 * Copyright:Copyright(c) 2018
 * CreateTime:18/5/2$  14:00$
 * @author 郑炯
 * @version 1.0
 */

class RecommendListFragment : BaseSupportFragment<IPresenter>() {

    companion object {
        @JvmStatic
        fun newInstance() = RecommendListFragment()
    }

    override fun setupFragmentComponent(appComponent: AppComponent) {

    }

    override fun setData(data: Any?) {

    }

    override fun initView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.recyclerview_layout, container, false)
    }

    override fun initData(savedInstanceState: Bundle?) {
        recyclerView.layoutManager = LinearLayoutManager(context)
        val adapter = RecommendListAdapter()
        recyclerView.addItemDecoration(
                HorizontalDividerItemDecoration.Builder(view!!.context)
                        .size(resources.getDimensionPixelSize(R.dimen.padding_8))
                        .colorResId(R.color.color_background)
                        .showLastDivider()
                        .build()
        )
        recyclerView.adapter = adapter
        adapter.setNewData(mutableListOf(
                RecommendListEntity(RecommendListEntity.TYPE_BANNER),
                RecommendListEntity(RecommendListEntity.TYPE_GRID_THREE),
                RecommendListEntity(RecommendListEntity.TYPE_GRID_THREE),
                RecommendListEntity(RecommendListEntity.TYPE_GRID_THREE),
                RecommendListEntity(RecommendListEntity.TYPE_GRID_THREE),
                RecommendListEntity(RecommendListEntity.TYPE_GRID_THREE),
                RecommendListEntity(RecommendListEntity.TYPE_GRID_THREE),
                RecommendListEntity(RecommendListEntity.TYPE_GRID_THREE),
                RecommendListEntity(RecommendListEntity.TYPE_GRID_THREE),
                RecommendListEntity(RecommendListEntity.TYPE_GRID_THREE),
                RecommendListEntity(RecommendListEntity.TYPE_GRID_THREE),
                RecommendListEntity(RecommendListEntity.TYPE_GRID_THREE),
                RecommendListEntity(RecommendListEntity.TYPE_GRID_THREE),
                RecommendListEntity(RecommendListEntity.TYPE_GRID_THREE),
                RecommendListEntity(RecommendListEntity.TYPE_GRID_THREE)
        ))
    }
}
