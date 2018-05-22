package org.namofo.radio.mvp.ui.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alibaba.android.arouter.facade.annotation.Route
import com.jess.arms.di.component.AppComponent
import com.jess.arms.mvp.IPresenter
import com.lzx.musiclibrary.aidl.model.SongInfo
import com.lzx.musiclibrary.constans.State
import com.lzx.musiclibrary.manager.MusicManager
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration
import kotlinx.android.synthetic.main.fragment_album_detail.*
import me.yokeyword.fragmentation.ISupportFragment
import org.namofo.radio.R
import org.namofo.radio.app.ARouterPath
import org.namofo.radio.mvp.ui.adapter.AlbumDetailListAdapter
import org.namofo.radio.mvp.ui.base.BaseSupportFragment

/**
 * 专辑详情
 * Copyright:Copyright(c) 2018
 * CreateTime:18/5/17$  19:51$
 * @author 郑炯
 * @version 1.0
 */

@Route(path = ARouterPath.ALBUM_DETAIL)
class AlbumDetailFragment : BaseSupportFragment<IPresenter>(), AlbumDetailListAdapter.OnItemClickListener {

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
        toolbar.title = "专辑详情"
        toolbar.setNavigationOnClickListener {
            pop()
        }
        recyclerView.addItemDecoration(
                HorizontalDividerItemDecoration
                        .Builder(context)
                        .size(2)
                        .colorResId(R.color.color_efefef).build()
        )
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = AlbumDetailListAdapter(this, mutableListOf("", "", "", "", "", "", "", "", "", "", "", ""))
    }

    override fun onItemClick() {
        val currentSongInfo = SongInfo().apply {
            songId = "123"
            //songUrl = "http://audio.xmcdn.com/group9/M0A/87/05/wKgDYldS66OhILGuAI7YXtdBSSk047.m4a"
            songUrl = "http://zhangmenshiting.qianqian.com/data2/music/61ae7c5e88d7218b9b78dc776a300764/591406259/591406259.mp3?xcode=3c83dfba9bbdcbbfd1823dca873f4874"
            duration = 397 * 1000
        }

        val musicManager = MusicManager.get()
        //如果正在播放的是当前音乐,并且正在播放, 就进入播放界面
        if (MusicManager.isCurrMusicIsPlaying(currentSongInfo)) {
            start(AudioDetailFragment.newInstance(currentSongInfo), ISupportFragment.STANDARD)
            return
        }else if (MusicManager.isCurrMusicIsPaused(currentSongInfo)) {//当前播放暂停
            start(AudioDetailFragment.newInstance(currentSongInfo), ISupportFragment.STANDARD)
            return
        }

        musicManager.playMusicByInfo(currentSongInfo)
    }
}