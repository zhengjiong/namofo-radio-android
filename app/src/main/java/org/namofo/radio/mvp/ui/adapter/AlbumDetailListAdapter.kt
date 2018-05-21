package org.namofo.radio.mvp.ui.adapter

import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.functions.Consumer
import org.namofo.radio.R
import org.namofo.radio.mvp.ui.holder.AlbumDetailListViewHolder

/**
 *
 * Copyright:Copyright(c) 2018
 * CreateTime:18/5/17$  21:04$
 * @author 郑炯
 * @version 1.0
 */

class AlbumDetailListAdapter(private val onItemClickListener: OnItemClickListener, list: MutableList<String>? = null) : BaseQuickAdapter<String, AlbumDetailListViewHolder>(R.layout.album_detail_list_item, list) {

    interface OnItemClickListener {
        fun onItemClick()
    }

    override fun convert(helper: AlbumDetailListViewHolder?, item: String?) {

        RxView.clicks(helper!!.tvTitle.parent as View).subscribe({
            onItemClickListener.onItemClick()
        }, { })

    }
}