package com.article.app.utils

import android.app.Activity
import com.google.android.material.snackbar.Snackbar

/**
 * @author vikas.grover
 * SnackBar Utils
 */
class SnackBarUtils {

    companion object {
        fun show(activity: Activity?, msg: String) {
            if (activity != null) {
                Snackbar
                    .make(
                        activity.window.decorView.findViewById(android.R.id.content),
                        validateString(msg),
                        Snackbar.LENGTH_LONG
                    ).show()

            }
        }
        private fun validateString(msg: String?): String {
            return msg ?: "null"
        }
    }

}