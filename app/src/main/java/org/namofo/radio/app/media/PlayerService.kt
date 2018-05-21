package org.namofo.radio.app.media

import android.app.Service
import android.content.Intent
import android.os.IBinder
import org.namofo.radio.app.media.RadioPlayer.RADIO_PLAY_OR_STOP_ACTION
import org.namofo.radio.utils.LogUtils


/**
 *
 * Copyright:Copyright(c) 2018
 * CreateTime:18/5/2$  22:44$
 * @author 郑炯
 * @version 1.0
 */

class PlayerService : Service() {

    override fun onBind(intent: Intent?): IBinder? {
        LogUtils.e("PlayerService onBind")
        return null
    }

    override fun onCreate() {
        super.onCreate()
        LogUtils.e("PlayerService onCreate ${Thread.currentThread()}")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        LogUtils.e("PlayerService onStartCommand startId=$startId,action=${intent?.action}")
        intent?.action?.run {
            when (this) {
                RADIO_PLAY_OR_STOP_ACTION -> {
                    RadioPlayer.playOrStop()
                }
            }
        }

        //如果在执行完onStartCommand后，服务被异常kill掉，系统会自动重启该服务。
        return Service.START_NOT_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        LogUtils.e("PlayerService onDestroy")
    }
}