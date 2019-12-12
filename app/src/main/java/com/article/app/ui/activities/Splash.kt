package com.article.app.ui.activities

import android.app.Activity
import android.os.Bundle
import android.view.Window
import com.article.app.R
import com.article.app.utils.ActivitySwitcher


/**
 * @author vikas.grover
 * Splash - Main Entry Point For the App
 */
class Splash : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_splash)
        ActivitySwitcher.switchActivity(this, ArticleList::class.java, withHandler = true, isFinish = true)
    }
}

