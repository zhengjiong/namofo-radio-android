package org.namofo.radio.mvp.ui.adapter

import com.bigkoo.convenientbanner.ConvenientBanner
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator
import com.blankj.utilcode.util.ConvertUtils
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import org.namofo.radio.R
import org.namofo.radio.mvp.model.entity.RecommendListEntity
import org.namofo.radio.mvp.ui.holder.RecommendBannerHolder

/**
 *
 * Copyright:Copyright(c) 2018
 * CreateTime:18/5/6$  14:59$
 * @author 郑炯
 * @version 1.0
 */


class RecommendListAdapter : BaseMultiItemQuickAdapter<RecommendListEntity, BaseViewHolder>(null) {

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

            }
        }

    }
}
