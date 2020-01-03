package com.article.app

import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.article.app.ui.activities.ArticleDetails
import com.article.app.ui.activities.ArticleList
import org.junit.Rule

class ArticleDetailsActivityTest{

    @Rule
    @JvmField
    val rule = ActivityScenarioRule<ArticleDetails>(ArticleDetails::class.java)






}