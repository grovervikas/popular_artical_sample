package com.article.app

import android.annotation.TargetApi
import android.app.Dialog
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.Toolbar

/**
 * @author vikas.grover
 * Base Activity - acquire common functionalists, properties : show progress, dismiss progress
 */
open class BaseActivity : AppCompatActivity(), LoaderHelper {

    private var dialog: Dialog? = null


    @TargetApi(Build.VERSION_CODES.KITKAT)
    fun toolBarSetup(toolbar: Toolbar) {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(
            AppCompatResources.getDrawable(
                this,
                R.drawable.ic_back_arrow
            )
        )
    }

    override fun showProgress(context: Context) {
        dialog = Dialog(context, R.style.TransactionProgressTheme)
        dialog?.setContentView(R.layout.custom_progress)
        dialog?.setCancelable(false)
        dialog?.setCanceledOnTouchOutside(false)
        dialog?.show()
    }

    override fun dismissProgress() {
        dialog?.dismiss()
    }

    fun getProgressDialog(): Dialog {
        return dialog!!
    }
}