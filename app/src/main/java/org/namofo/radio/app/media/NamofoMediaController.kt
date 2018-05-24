package org.namofo.radio.app.media

import android.content.Context
import android.content.Intent

/**
 *
 * Copyright:Copyright(c) 2018
 * CreateTime:18/5/2$  17:30$
 * @author 郑炯
 * @version 1.0
 */

class NamofoMediaController {

    companion object {
        @JvmStatic
        fun radioPlayOrStop(context: Context) {
            Intent(context, PlayerService::class.java).apply {
                action = RadioPlayer.RADIO_PLAY_OR_STOP_ACTION
                context.startService(this)
            }
        }

        @JvmStatic
        fun radioStop(context: Context) {
            Intent(context, PlayerService::class.java).apply {
                action = RadioPlayer.RADIO_STOP_ACTION
                context.startService(this)
            }
        }
    }
}