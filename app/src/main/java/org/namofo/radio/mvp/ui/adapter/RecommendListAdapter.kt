package org.namofo.radio.mvp.ui.adapter

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import com.bigkoo.convenientbanner.ConvenientBanner
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator
import com.blankj.utilcode.util.ConvertUtils
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.yanyusong.y_divideritemdecoration.Y_Divider
import com.yanyusong.y_divideritemdecoration.Y_DividerBuilder
import com.yanyusong.y_divideritemdecoration.Y_DividerItemDecoration
import org.namofo.radio.R
import org.namofo.radio.mvp.model.entity.RecommendListEntity
import org.namofo.radio.mvp.ui.holder.RecommendBannerHolder
import org.namofo.radio.utils.GridOffsetsItemDecoration

/**
 *
 * Copyright:Copyright(c) 2018
 * CreateTime:18/5/6$  14:59$
 * @author 郑炯
 * @version 1.0
 */


class RecommendListAdapter(private val onItemClickListener: GridAlbumAdapter.OnItemClickListener) : BaseMultiItemQuickAdapter<RecommendListEntity, BaseViewHolder>(null) {

    init {
        addItemType(RecommendListEntity.TYPE_BANNER, R.layout.recommend_banner)
        addItemType(RecommendListEntity.TYPE_GRID_THREE, R.layout.recommend_grid_three)
    }

    override fun convert(helper: BaseViewHolder?, item: RecommendListEntity?) {

        when (helper!!.itemViewType) {
            RecommendListEntity.TYPE_BANNER -> {
                val banner = helper.getView<ConvenientBanner<String>>(R.id.banner)
                banner.setPages(object : CBViewHolderCreator<RecommendBannerHolder> {
                    override fun createHolder(): RecommendBannerHolder {
                        return RecommendBannerHolder(ConvertUtils.dp2px(180F))
                    }
                }, mutableListOf("", "", "", "", "", "", "", "", ""))
                        .setPageIndicator(intArrayOf(R.drawable.ic_page_card_indicator, R.drawable.ic_page_card_indicator_selected))
                        .setPointViewVisible(true).isCanLoop = true

                banner.startTurning(3000)
            }
            RecommendListEntity.TYPE_GRID_THREE -> {
                val recyclerView = helper.getView<RecyclerView>(R.id.recyclerView)
                recyclerView.layoutManager = GridLayoutManager(helper.itemView.context, 3, GridLayoutManager.VERTICAL, false)
                recyclerView.itemDecorationCount.takeIf {
                    it <= 0
                }?.run {
                    recyclerView.addItemDecoration(object : Y_DividerItemDecoration(helper.itemView.context) {
                        override fun getDivider(itemPosition: Int): Y_Divider {
                            var divider: Y_Divider = Y_DividerBuilder()
                                    .setLeftSideLine(
                                            true,
                                            helper.itemView.resources.getColor(android.R.color.transparent),
                                            4F, 0F, 0f)
                                    .setRightSideLine(
                                            true,
                                            helper.itemView.resources.getColor(android.R.color.transparent),
                                            4F, 0F, 0f)
                                    .setBottomSideLine(
                                            true,
                                            helper.itemView.resources.getColor(android.R.color.transparent),
                                            8F, 0F, 0f)
                                    .create()
                            return divider
                        }
                    })
                }
                val gridAlbumAdapter = GridAlbumAdapter(onItemClickListener, R.layout.recommend_grid_item, mutableListOf("", "", "", "", "", ""))
                recyclerView.adapter = gridAlbumAdapter
            }
        }

    }
}
