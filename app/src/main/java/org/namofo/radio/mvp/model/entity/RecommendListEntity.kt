package org.namofo.radio.mvp.model.entity

import com.chad.library.adapter.base.entity.MultiItemEntity

/**
 *
 * Copyright:Copyright(c) 2018
 * CreateTime:18/5/6$  14:59$
 * @author 郑炯
 * @version 1.0
 */

class RecommendListEntity(private val itemType: Int) : MultiItemEntity {
    companion object {
        const val TYPE_BANNER = 1
        const val TYPE_GRID_THREE = 2
    }

    override fun getItemType() = itemType
}