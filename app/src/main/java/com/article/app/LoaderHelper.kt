package com.article.app

import android.content.Context

/**
 * @author vikas.grover
 * Callbacks to handle Progress Dialog : Loader View
 */
interface LoaderHelper {

    fun showProgress(context: Context)
    fun dismissProgress()
}