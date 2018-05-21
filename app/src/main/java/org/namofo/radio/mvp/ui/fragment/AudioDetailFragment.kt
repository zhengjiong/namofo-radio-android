package org.namofo.radio.mvp.ui.fragment

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alibaba.android.arouter.facade.annotation.Route
import com.jess.arms.di.component.AppComponent
import com.jess.arms.mvp.IPresenter
import kotlinx.android.synthetic.main.fragment_album_detail.*
import org.namofo.radio.R
import org.namofo.radio.app.ARouterPath
import org.namofo.radio.mvp.ui.base.BaseSupportFragment

/**
 * 播放界面
 *
 * Copyright:Copyright(c) 2018
 * CreateTime:18/5/19$  11:07$
 * @author 郑炯
 * @version 1.0
 */
@Route(path = ARouterPath.AUDIO_DETAIL)
class AudioDetailFragment : BaseSupportFragment<IPresenter>() {

    companion object {
        @JvmStatic
        fun newInstance() = AudioDetailFragment()
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
    }

}