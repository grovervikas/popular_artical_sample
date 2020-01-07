package com.article.app.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import com.article.app.BaseViewModelActivity
import com.article.app.Const
import com.article.app.R
import com.article.app.databinding.ActivityArticleListBinding
import com.article.app.netio.ApiConst
import com.article.app.netio.BaseApiClient
import com.article.app.netio.RetrofitInterface
import com.article.app.utils.ActivitySwitcher
import com.article.app.utils.SnackBarUtils
import com.article.app.vm.ArticleListViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_article_list.*

/**
 * @author vikas.grover
 * Articles List : Displays List Of Articles
 */
class ArticleList : BaseViewModelActivity<ArticleListViewModel>() {

    private val articleListViewModel = ArticleListViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)
        val binding = DataBindingUtil.setContentView<ViewDataBinding>(this, R.layout.activity_article_list) as ActivityArticleListBinding
        binding.articleListViewModel = articleListViewModel
        articleListViewModel.showDismissProgressDialog.observe(this, Observer { if(it){
            showProgress(this)}else{dismissProgress()}
        })
        articleListViewModel.showDismissSnackBar.observe(this, Observer { SnackBarUtils.show(this,it) })
        articleListViewModel.proceedToArticleDetails.observe(this, Observer {
            val  intent = Intent(this, ArticleDetails::class.java)
            intent.putExtra(Const.KEY_INTENT_PRODUCT_DETAILS,it)
            ActivitySwitcher.switchActivity(this, intent, withHandler = false, isFinish = false)
        })
        articleListViewModel.int(this, binding)
        articleListViewModel.setAppServiceInterface(BaseApiClient.getClient(ApiConst.BASE_URL)
            .create(RetrofitInterface::class.java), AndroidSchedulers.mainThread())
        articleListViewModel.articleList()
        articleListViewModel.mutablePostList.observe(this, Observer { articleListViewModel.populateArticles(it) })
        articleListViewModel.openImageInBig.observe(this, Observer {
            val  intent = Intent(this, BiggerImage::class.java)
            intent.putExtra(Const.KEY_INTENT_PRODUCT_DETAILS,it)
            ActivitySwitcher.switchActivity(this, intent, withHandler = false, isFinish = false) })
    }

    override fun onDestroy() {
        super.onDestroy()
        articleListViewModel.decomposableObject()?.dispose()
    }
}