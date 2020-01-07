package com.article.app.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import com.article.app.BaseViewModelActivity
import com.article.app.Const
import com.article.app.R
import com.article.app.databinding.ActivityArticleDetailsBinding
import com.article.app.model.Article
import com.article.app.netio.ApiConst
import com.article.app.netio.BaseApiClient
import com.article.app.netio.RetrofitInterface
import com.article.app.utils.ActivitySwitcher
import com.article.app.utils.SnackBarUtils
import com.article.app.vm.ArticleDetailsViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
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
        val binding = DataBindingUtil.setContentView<ViewDataBinding>(
            this,
            R.layout.activity_article_details
        ) as ActivityArticleDetailsBinding
        binding.articleDetailsViewModel = articleDetailsViewModel
        binding.article = Article("", "", "", "", "", "", "", ArrayList())
        articleDetailsViewModel.int(this, binding)
        articleDetailsViewModel.finishMe.observe(this, Observer { finish() })
        articleDetailsViewModel.showDismissProgressDialog.observe(this, Observer {
            if (it) {
                showProgress(this)
            } else {
                dismissProgress()
            }
        })
        articleDetailsViewModel.setAppServiceInterface(
            BaseApiClient.getClient(ApiConst.BASE_URL)
                .create(RetrofitInterface::class.java), AndroidSchedulers.mainThread()
        )
        if (intent == null || !intent.hasExtra(Const.KEY_INTENT_PRODUCT_DETAILS)) return
        articleDetailsViewModel.articleItem((intent.getSerializableExtra(Const.KEY_INTENT_PRODUCT_DETAILS) as Article).id)
        articleDetailsViewModel.mutableArticleItem.observe(this, Observer {
            articleDetailsViewModel.showItem(it)
        })
        articleDetailsViewModel.showDismissSnackBar.observe(
            this,
            Observer { SnackBarUtils.show(this, it) })
        articleDetailsViewModel.openBiggerImage.observe(this, Observer {
            val intent = Intent(this, BiggerImage::class.java)
            intent.putExtra(Const.KEY_INTENT_PRODUCT_DETAILS, articleDetailsViewModel.mutableArticleItem.value)
            ActivitySwitcher.switchActivity(this, intent, withHandler = false, isFinish = false)
        })
    }
}