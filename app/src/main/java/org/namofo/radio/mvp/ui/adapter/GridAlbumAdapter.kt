package org.namofo.radio.mvp.ui.adapter

import android.support.annotation.LayoutRes
import android.widget.ImageView
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.jakewharton.rxbinding2.view.RxView
import com.jess.arms.http.imageloader.glide.GlideArms
import io.reactivex.functions.Consumer
import org.namofo.radio.R
import java.util.concurrent.TimeUnit

/**
 *
 * Copyright:Copyright(c) 2018
 * CreateTime:18/5/10$  13:51$
 * @author 郑炯
 * @version 1.0
 */


class GridAlbumAdapter(private val onItemClickListener: GridAlbumAdapter.OnItemClickListener, @LayoutRes layoutResId: Int, data: MutableList<String>) : BaseQuickAdapter<String, BaseViewHolder>(layoutResId, data) {

    interface OnItemClickListener {
        fun onItemClick()
    }

    override fun convert(helper: BaseViewHolder, item: String?) {
        val icon = helper.getView<ImageView>(R.id.icon)
        val title = helper.getView<TextView>(R.id.tv_title)

        title.text = "专辑名称xxx"
        GlideArms.with(helper.itemView.context)
                .load("https://1919-new-bbc-pro.oss-cn-beijing.aliyuncs.com/487bf1a1-0693-467b-aa50-c1ccf211cdaa?x-oss-process=image/resize,p_100")
                .centerCrop()
                .into(icon)

        RxView.clicks(icon).throttleFirst(300L, TimeUnit.MILLISECONDS)
                .subscribe({
                    onItemClickListener.onItemClick()
                }, {
                })
    }


}