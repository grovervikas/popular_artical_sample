package com.article.app

import androidx.appcompat.widget.Toolbar

/**
 * @author vikas.grover
 * Abstract base view model activity
 */
abstract class BaseViewModelActivity<VM : BaseViewModel> : BaseActivity(){

    protected fun toolBarSetUp(toolbar: Toolbar) {
        toolBarSetup(toolbar)
        toolbar.title = ""
    }
}