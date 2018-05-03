package org.namofo.radio.mvp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jakewharton.rxbinding2.view.RxView
import com.jess.arms.di.component.AppComponent
import com.jess.arms.mvp.IPresenter
import com.jess.arms.utils.RxLifecycleUtils
import kotlinx.android.synthetic.main.fragment_radio.*
import org.namofo.radio.R
import org.namofo.radio.app.media.NamofoMediaController
import org.namofo.radio.app.media.RadioPlayer
import org.namofo.radio.mvp.model.event.RadioControlEvent
import org.namofo.radio.mvp.ui.base.BaseSupportFragment
import org.simple.eventbus.Subscriber

/**
 *
 * Copyright:Copyright(c) 2018
 * CreateTime:18/5/2$  14:46$
 * @author 郑炯
 * @version 1.0
 */

class RadioFragment : BaseSupportFragment<IPresenter>() {

    companion object {
        @JvmStatic
        fun newInstance() = RadioFragment()
    }

    override fun setupFragmentComponent(appComponent: AppComponent) {

    }

    override fun setData(data: Any?) {

    }

    override fun initView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_radio, container, false)
    }

    override fun initData(savedInstanceState: Bundle?) {
        RxView.clicks(controlContainer)
                .compose(RxLifecycleUtils.bindToLifecycle(this))
                .subscribe({
                    NamofoMediaController.radioPlayOrStop(context!!)
                }, {})
    }

    @Subscriber
    fun onEventMainThread(event: RadioControlEvent) {
        when (event.state) {
            RadioPlayer.RADIO_PLAY_ACTION -> {
                icPlay.setImageResource(R.drawable.vector_radio_stop)
            }
            RadioPlayer.RADIO_STOP_ACTION -> {
                icPlay.setImageResource(R.drawable.vector_radio_play)
            }
        }
    }

}