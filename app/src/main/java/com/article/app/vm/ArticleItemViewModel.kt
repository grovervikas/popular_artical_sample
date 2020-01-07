package com.article.app.vm

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.article.app.BaseViewModel
import com.article.app.Const
import com.article.app.R
import com.article.app.model.Article
import com.article.app.utils.CircleTransform
import com.squareup.picasso.Picasso
import java.util.*


/**
 * @author vikas.grover
 * Article Item View Model
 */
class ArticleItemViewModel : BaseViewModel() {

    private var article: Article? = null
    private var productItemListener: ArticleItemListener? = null

    override fun update(p0: Observable?, p1: Any?) {
    }

    fun onDataAvailable(article: Article, productItemListener: ArticleItemListener) {
        this.article = article
        this.productItemListener = productItemListener
    }

    fun title() = article!!.title

    fun thumbnail() = article!!.thumbnail

    fun created_at() = article!!.created_at

    fun update_at() = article!!.updated_at

    fun about() = article!!.about

    fun author() = article!!.author

    fun showImageInBiggerSize() {
        productItemListener?.openImageInBigSize(article!!)
    }


    fun onItemClick() {
        productItemListener?.handleItemClick(article!!)
    }

    companion object {
        @JvmStatic
        @BindingAdapter("imageUrl")
        fun setImageUrl(imageView: ImageView, url: String?) {
            if (url.isNullOrEmpty()) return
            Picasso.get().load(url)
                .transform(CircleTransform())
                .resize(Const.MAX_WIDTH, Const.MAX_HEIGHT)
                .centerCrop()
                .placeholder(R.drawable.ic_place_holder)
                .error(R.drawable.ic_place_holder)
                .into(imageView)
        }

        @JvmStatic
        @BindingAdapter("articleUrl")
        fun setarticleUrl(imageView: ImageView, url: String?) {
            if (url.isNullOrEmpty()) return
            Picasso.get().load(url)
                .resize(Const.MAX_WIDTH, Const.MAX_HEIGHT)
                .centerCrop()
                .placeholder(R.drawable.ic_place_holder)
                .error(R.drawable.ic_place_holder)
                .into(imageView)
        }
    }


interface ArticleItemListener {
        fun handleItemClick(article: Article)
        fun openImageInBigSize(article: Article)
    }

}