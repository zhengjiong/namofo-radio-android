package org.namofo.radio.utils

import android.util.Log
import org.namofo.radio.BuildConfig
import timber.log.Timber

/**
 *
 * Copyright:Copyright(c) 2018
 * CreateTime:18/5/2$  18:04$
 * @author 郑炯
 * @version 1.0
 */


class LogUtils {
    companion object {
        private const val TAG = "namofo"

        @JvmStatic
        fun e(content: String?) {
            if (BuildConfig.DEBUG && content != null) {
                //Timber.tag(TAG).e(content)
                Log.e(TAG, content)
            }
        }
    }
}