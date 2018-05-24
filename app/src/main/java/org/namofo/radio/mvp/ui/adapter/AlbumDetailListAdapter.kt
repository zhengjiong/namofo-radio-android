package org.namofo.radio.mvp.ui.adapter

import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.jakewharton.rxbinding2.view.RxView
import com.lzx.musiclibrary.aidl.model.SongInfo
import com.lzx.musiclibrary.manager.MusicManager
import io.reactivex.functions.Consumer
import org.namofo.radio.R
import org.namofo.radio.mvp.ui.holder.AlbumDetailListViewHolder
import java.util.concurrent.TimeUnit

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

    override fun convert(helper: AlbumDetailListViewHolder, item: String?) {
        //helper.tvTitle.setText(item)
        helper.tvTitle.text = "标题选中状态标题选中状态标题选中状态"
        helper.tvTitle.isSelected = MusicManager.isCurrMusicIsPlayingMusic(SongInfo().apply {
            songId = "123"
            //songUrl = "http://audio.xmcdn.com/group9/M0A/87/05/wKgDYldS66OhILGuAI7YXtdBSSk047.m4a"
            songUrl = "http://zhangmenshiting.qianqian.com/data2/music/61ae7c5e88d7218b9b78dc776a300764/591406259/591406259.mp3?xcode=3c83dfba9bbdcbbfd1823dca873f4874"
            duration = 397 * 1000
        })


        RxView.clicks(helper.tvTitle.parent as View)
                .throttleFirst(300, TimeUnit.MILLISECONDS)
                .subscribe({
                    onItemClickListener.onItemClick()
                }, { })

    }
}