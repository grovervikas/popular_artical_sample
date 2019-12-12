package com.article.app.vm

import android.content.Context
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import com.article.app.BaseViewModel
import com.article.app.R
import com.article.app.databinding.ActivityArticleDetailsBinding
import com.article.app.model.Article
import com.article.app.utils.SingleLiveEvent
import com.squareup.picasso.Picasso
import java.util.*

/**
 * @author vikas.grover
 * Article Details View Model
 */
class ArticleDetailsViewModel : BaseViewModel() {

    private var context: Context? = null
    private var binding: ActivityArticleDetailsBinding? = null
    var article: Article? = null
    private val _finishMe = SingleLiveEvent<String>()
    val finishMe: LiveData<String> get() = _finishMe

    fun int(context: Context, binding: ActivityArticleDetailsBinding) {
        this.context = context
        this.binding = binding
    }

    override fun update(p0: Observable?, p1: Any?) {
    }

    fun title() = article!!.title

    fun thumbnail() = article!!.thumbnail

    fun created_at() = article!!.created_at

    fun update_at() = article!!.updated_at

    fun about() = article!!.about

    fun author() = article!!.author

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
}