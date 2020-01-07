package com.article.app.vm

import android.content.Context
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.article.app.BaseViewModel
import com.article.app.R
import com.article.app.databinding.ActivityArticleDetailsBinding
import com.article.app.model.Article
import com.article.app.netio.RetrofitInterface
import com.article.app.utils.SingleLiveEvent
import com.squareup.picasso.Picasso
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.*

/**
 * @author vikas.grover
 * Article Details View Model
 */
class ArticleDetailsViewModel : BaseViewModel() {

    private var context: Context? = null
    private var binding: ActivityArticleDetailsBinding? = null
    private val _finishMe = SingleLiveEvent<String>()
    val finishMe: LiveData<String> get() = _finishMe
    val mutableArticleItem: MutableLiveData<Article> = MutableLiveData()
        get() = field
    private lateinit var appService: RetrofitInterface
    private lateinit var schedulers: Scheduler
    private var myCompositeDisposable: CompositeDisposable? = null
    private val _showDismissProgressDialog = SingleLiveEvent<Boolean>()
    val showDismissProgressDialog: LiveData<Boolean> get() = _showDismissProgressDialog
    private val _showDismissSnackBar = SingleLiveEvent<String>()
    val showDismissSnackBar: LiveData<String> get() = _showDismissSnackBar
    private val _openBiggerImage = SingleLiveEvent<Article>()
    val openBiggerImage: LiveData<Article> get() = _openBiggerImage

    fun int(context: Context, binding: ActivityArticleDetailsBinding) {
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


    fun finishMe() {
        _finishMe.call()
    }

    companion object {
        @JvmStatic
        @BindingAdapter("imageUrl")
        fun setImageUrl(imageView: ImageView, url: String?) {
            Picasso.get().load(url).placeholder(R.drawable.ic_place_holder)
                .error(R.drawable.ic_place_holder)
                .into(imageView)
        }
    }


    // get article list
    fun articleItem(itemIndex: String) {
//        if (!NetworkUtils.isNetworkAvailable(context!!)) {
//            _showDismissSnackBar.value = context?.resources?.getString(R.string.no_internet_connection)
//            return
//        }
        _showDismissProgressDialog.value = true
        decomposableObject()?.add(
            getAppServiceInterface().articleItem(itemIndex)
                .observeOn(schedulers)
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError)
        )
    }

    /************************* Handle Api Response  ***************/
    // api response  :
    private fun handleResponse(article: Article) {
        mutableArticleItem.value = article
    }


    fun showItem(article: Article) {
        _showDismissProgressDialog.value = false
        binding?.article = article
    }

    // handle api error
    private fun handleError(error: Throwable) {
        _showDismissSnackBar.value = error.message!!
        _showDismissProgressDialog.value = false
    }

    fun decomposableObject() = myCompositeDisposable

    fun openBiggerImage(){
        _openBiggerImage.value = mutableArticleItem.value
    }

}