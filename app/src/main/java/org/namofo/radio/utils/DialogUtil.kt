package org.namofo.radio.utils

import android.content.Context
import android.content.DialogInterface
import android.support.v7.app.AlertDialog

/**
 *
 * CreateTime:18/5/21  18:09
 * @author 郑炯
 * @version 1.0
 */


class DialogUtil {
    companion object {
        @JvmStatic
        fun createDialog(context: Context, message: String, listener: DialogInterface.OnClickListener) {
            AlertDialog.Builder(context)
                    .setMessage(message)
                    .setPositiveButton("确定", listener)
                    .create()
        }
    }
}