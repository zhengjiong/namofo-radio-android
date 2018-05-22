package org.namofo.radio.mvp.ui.base

import android.content.DialogInterface
import com.jess.arms.mvp.IPresenter
import org.namofo.radio.utils.DialogUtil

/**
 *
 * CreateTime:18/5/21  18:15
 * @author 郑炯
 * @version 1.0
 */


abstract class BaseNamofoFragment<P : IPresenter> : BaseSupportFragment<P>() {

    fun error(message: String) {
        DialogUtil.createDialog(context!!, message, DialogInterface.OnClickListener { dialog, which ->
            dialog.dismiss()
        })
    }

    fun errorNoCancel(message: String) {
        DialogUtil.createDialog(context!!, message, DialogInterface.OnClickListener { dialog, which ->
            dialog.dismiss()
            pop()
        })
    }
}