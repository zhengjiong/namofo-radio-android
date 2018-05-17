package org.namofo.radio.mvp.ui.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import org.namofo.radio.R
import org.namofo.radio.mvp.ui.holder.AlbumDetailListViewHolder

/**
 *
 * Copyright:Copyright(c) 2018
 * CreateTime:18/5/17$  21:04$
 * @author 郑炯
 * @version 1.0
 */

class AlbumDetailListAdapter(list: MutableList<String>? = null) : BaseQuickAdapter<String, AlbumDetailListViewHolder>(R.layout.album_detail_list_item, list) {

    override fun convert(helper: AlbumDetailListViewHolder?, item: String?) {

    }
}