package org.namofo.radio.mvp.ui.holder

import android.view.View
import android.widget.TextView
import com.chad.library.adapter.base.BaseViewHolder
import org.namofo.radio.R

/**
 *
 * Copyright:Copyright(c) 2018
 * CreateTime:18/5/17$  21:39$
 * @author 郑炯
 * @version 1.0
 */

class AlbumDetailListViewHolder(view: View) : BaseViewHolder(view) {
    val tvTitle = view.findViewById<TextView>(R.id.tv_title)
    val tvDate = view.findViewById<TextView>(R.id.tv_date)
    val tvTimesOfPlay = view.findViewById<TextView>(R.id.tv_times_of_play)
    val tvDuration = view.findViewById<TextView>(R.id.tv_duration)
}