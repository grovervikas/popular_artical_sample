package com.article.app.vm

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.article.app.BaseViewModel
import com.article.app.R
import com.article.app.adapter.ArticleListAdapter
import com.article.app.databinding.ActivityArticleListBinding
import com.article.app.model.Article
import com.article.app.netio.ApiConst
import com.article.app.netio.BaseApiClient
import com.article.app.netio.RetrofitInterface
import com.article.app.utils.NetworkUtils
import com.article.app.utils.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.*
import kotlin.collections.ArrayList

/**
 * @author vikas.grover
 * Article List View Model : To list
 */
class ArticleListViewModel : BaseViewModel(), ArticleListAdapter.ArticleAdapterListener {

    private var context: Context? = null
    private var binding: ActivityArticleListBinding? = null
    private val _showDismissSnackBar = SingleLiveEvent<String>()
    val showDismissSnackBar: LiveData<String> get() = _showDismissSnackBar
    private val _showDismissProgressDialog = SingleLiveEvent<Boolean>()
    val showDismissProgressDialog: LiveData<Boolean> get() = _showDismissProgressDialog
    private var myCompositeDisposable: CompositeDisposable? = null
    private val _proceedToProductDetails = SingleLiveEvent<Article>()
    val proceedToArticleDetails: LiveData<Article> get() = _proceedToProductDetails
    private var articleList: ArrayList<Article> = ArrayList()

    fun int(context: Context, binding: ActivityArticleListBinding) {
        this.context = context
        this.binding = binding
        myCompositeDisposable = CompositeDisposable()
        articleList()
    }

    override fun update(p0: Observable?, p1: Any?) {
    }

    // get article list
    private fun articleList() {
        if (!NetworkUtils.isNetworkAvailable(context!!)) {
            _showDismissSnackBar.value = context?.resources?.getString(R.string.no_internet_connection)
            return
        }
        _showDismissProgressDialog.value = true
        val appService = BaseApiClient.getClient(ApiConst.BASE_URL)
            .create(RetrofitInterface::class.java)
        decomposableObject()?.add(
            appService.articles()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError)
        )
    }

    /************************* Handle Api Response  ***************/

    // api response  : list of articles
    private fun handleResponse(list: List<Article>) {
        _showDismissProgressDialog.value = false
        if (list.isNullOrEmpty()) {
            _showDismissSnackBar.value = context?.resources?.getString(R.string.product_list_empty)
            return
        }

        articleList.addAll(list)
        binding?.listArticles?.layoutManager = LinearLayoutManager(context)
        binding?.listArticles?.adapter = ArticleListAdapter(articleList, this)
    }

    // handle api error
    private fun handleError(error: Throwable) {
        _showDismissSnackBar.value = error.message!!
        _showDismissProgressDialog.value = false
    }

    fun decomposableObject() = myCompositeDisposable

    override fun adapterItemClick(article: Article) {
        _proceedToProductDetails.value = article
    }

}