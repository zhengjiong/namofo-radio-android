package org.namofo.radio.utils

import android.app.Activity
import com.bilibili.boxing.Boxing
import com.bilibili.boxing.model.config.BoxingConfig
import com.bilibili.boxing_impl.ui.BoxingActivity
import org.namofo.radio.R

/**
 *
 * Copyright:Copyright(c) 2018
 * CreateTime:18/4/27$  16:12$
 * @author 郑炯
 * @version 1.0
 */


class Utils {
    companion object {

        @JvmStatic
        fun startBoxing(activity: Activity) {
            Boxing.of(BoxingConfig(BoxingConfig.Mode.SINGLE_IMG).needCamera(R.mipmap.ic_boxing_camera_white))
                    .withIntent(activity, BoxingActivity::class.java)
                    .start(activity, RequestCode.REQUEST_CODE_BOXING)

        }
    }
}