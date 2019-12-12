package com.article.app.ui.activities

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import com.article.app.BaseViewModelActivity
import com.article.app.Const
import com.article.app.R
import com.article.app.databinding.ActivityArticleDetailsBinding
import com.article.app.model.Article
import com.article.app.vm.ArticleDetailsViewModel
import kotlinx.android.synthetic.main.activity_article_list.*

/**
 * @author Vikas.grover
 * About Article
 */
class ArticleDetails : BaseViewModelActivity<ArticleDetailsViewModel>() {

    private val articleDetailsViewModel = ArticleDetailsViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)
        val binding = DataBindingUtil.setContentView<ViewDataBinding>(this, R.layout.activity_article_details) as ActivityArticleDetailsBinding
        binding.articleDetailsViewModel = articleDetailsViewModel
        articleDetailsViewModel.int(this, binding)
        articleDetailsViewModel.finishMe.observe(this, Observer { finish() })
        getIntentAndPass()
    }

    private fun getIntentAndPass(){
        if(intent!=null && intent.hasExtra(Const.KEY_INTENT_PRODUCT_DETAILS))
            articleDetailsViewModel.article = intent.getSerializableExtra(Const.KEY_INTENT_PRODUCT_DETAILS) as Article
    }
}