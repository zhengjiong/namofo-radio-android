package org.namofo.radio.mvp.ui.holder

import android.content.Context
import android.support.v7.widget.AppCompatImageView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bigkoo.convenientbanner.holder.Holder
import com.jess.arms.http.imageloader.glide.GlideArms

/**
 *
 * Copyright:Copyright(c) 2018
 * CreateTime:18/5/6$  15:26$
 * @author 郑炯
 * @version 1.0
 */

class RecommendBannerHolder(val bannerHeight: Int) : Holder<String> {
    lateinit var icon: AppCompatImageView

    override fun UpdateUI(context: Context?, position: Int, data: String?) {

    }

    override fun createView(context: Context?): View {
        icon = AppCompatImageView(context)
        icon.scaleType = ImageView.ScaleType.FIT_XY
        icon.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, bannerHeight)

        GlideArms.with(context!!)
                .load("https://1919-new-bbc-test.oss-cn-beijing.aliyuncs.com/d394f3dd-3f64-4d0d-9f53-77304c8ad32f")
                .into(icon)
        return icon
    }
}
