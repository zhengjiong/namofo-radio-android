package org.namofo.radio.mvp.ui.fragment

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alibaba.android.arouter.facade.annotation.Route
import com.jakewharton.rxbinding2.view.RxView
import com.jess.arms.di.component.AppComponent
import com.jess.arms.mvp.IPresenter
import com.jess.arms.utils.RxLifecycleUtils
import com.lzx.musiclibrary.aidl.model.SongInfo
import com.lzx.musiclibrary.constans.State
import com.lzx.musiclibrary.manager.MusicManager
import kotlinx.android.synthetic.main.fragment_audio_detail.*
import kotlinx.android.synthetic.main.toolbar.*
import org.namofo.radio.R
import org.namofo.radio.app.ARouterPath
import org.namofo.radio.mvp.ui.base.BaseFragment
import org.namofo.radio.mvp.ui.base.BaseSupportFragment
import org.namofo.radio.utils.IntentKey

/**
 * 播放界面
 *
 * Copyright:Copyright(c) 2018
 * CreateTime:18/5/19$  11:07$
 * @author 郑炯
 * @version 1.0
 */
@Route(path = ARouterPath.AUDIO_DETAIL)
class AudioDetailFragment : BaseFragment<IPresenter>() {
    var mSongInfo: SongInfo? = null

    companion object {
        @JvmStatic
        fun newInstance(songInfo: SongInfo): AudioDetailFragment {
            return AudioDetailFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(IntentKey.KEY_SONG_INFO, songInfo)
                }
            }
        }
    }

    override fun setupFragmentComponent(appComponent: AppComponent) {

    }

    override fun setData(data: Any?) {

    }

    override fun initView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_audio_detail, container, false)
    }

    override fun initData(savedInstanceState: Bundle?) {
        view?.setBackgroundColor(ContextCompat.getColor(context!!, R.color.white))
        toolbar.title = "媒体名称"
        toolbar.setNavigationOnClickListener {
            pop()
        }

        mSongInfo = arguments?.getParcelable(IntentKey.KEY_SONG_INFO)

        if (mSongInfo == null) {
            errorNoCancel("获取媒体名称出错")
            return
        }
        RxView.clicks(ivPlayer).compose(RxLifecycleUtils.bindToLifecycle(this))
                .subscribe({
                    val musicManager = MusicManager.get()
                    if (State.STATE_ASYNC_LOADING == musicManager.status
                            || State.STATE_PLAYING == musicManager.status) {
                        ivPlayer.setImageResource(R.mipmap.ic_bf_n)
                    } else {
                        ivPlayer.setImageResource(R.mipmap.ic_pause_n)
                    }
                    val currentSongInfo = SongInfo().also {
                        it.songId = "123"
                        it.songUrl = "http://audio.xmcdn.com/group9/M0A/87/05/wKgDYldS66OhILGuAI7YXtdBSSk047.m4a"
                    }

                    musicManager.playMusicByInfo(currentSongInfo)
                }) {
                }
    }

}