package com.article.app.vm

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.article.app.BaseViewModel
import com.article.app.R
import com.article.app.adapter.ArticleListAdapter
import com.article.app.databinding.ActivityArticleListBinding
import com.article.app.model.Article
import com.article.app.netio.RetrofitInterface
import com.article.app.utils.SingleLiveEvent
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.BiFunction
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
    val mutablePostList: MutableLiveData<List<Article>> = MutableLiveData()
        get() = field
    private lateinit var appService: RetrofitInterface
    private lateinit var schedulers: Scheduler
    private val _openImageInBig = SingleLiveEvent<Article>()
    val openImageInBig: LiveData<Article> get() = _openImageInBig


    fun int(context: Context, binding: ActivityArticleListBinding) {
        this.context = context
        this.binding = binding
    }

    fun getAppServiceInterface() = appService

    fun setAppServiceInterface(appService: RetrofitInterface, schedulers: Scheduler) {
        this.appService = appService
        myCompositeDisposable = CompositeDisposable()
        this.schedulers = schedulers
    }

    override fun update(p0: Observable?, p1: Any?) {
    }


    private fun combineResult(
        articleListOne: List<Article>,
        articleListTwo: List<Article>
    ): List<Article> {
        if (articleListOne.isNullOrEmpty() || articleListTwo.isNullOrEmpty()) return ArrayList()
        for ((index, value) in articleListOne.withIndex()) {
            if (value.id == articleListTwo[index].id) {
                articleListOne.get(index).images = emptyList()
                articleListOne.get(index).images = articleListTwo.get(index).images
            }
        }
        return articleListOne;
    }


    // get article list
    fun articleList() {
//        if (!NetworkUtils.isNetworkAvailable(context!!)) {
//            _showDismissSnackBar.value = context?.resources?.getString(R.string.no_internet_connection)
//            return
//        }
        _showDismissProgressDialog.value = true

        decomposableObject()?.add(
            io.reactivex.Observable.combineLatest(getAppServiceInterface()
                .articles(),
                getAppServiceInterface().images(),
                BiFunction<List<Article>, List<Article>, List<Article>> { t1: List<Article>, t2: List<Article> ->
                    combineResult(t1, t2)

                }).subscribeOn(Schedulers.io())
                .observeOn(schedulers)
                .subscribe(this::handleResponse, this::handleError)
        )
    }

    /************************* Handle Api Response  ***************/

    // api response  : list of articles
    private fun handleResponse(list: List<Article>) {
        mutablePostList.value = list
    }

    // populate List
    fun populateArticles(list: List<Article>) {
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

    override fun adapterImageClick(article: Article) {
        _openImageInBig.value = article
    }

}