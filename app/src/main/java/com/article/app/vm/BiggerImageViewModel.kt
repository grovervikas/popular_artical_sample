package com.article.app.vm

import android.content.Context
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.article.app.BaseViewModel
import com.article.app.R
import com.article.app.adapter.ImageSliderAdapter
import com.article.app.databinding.ActivityBiggerImageBinding
import com.article.app.model.Article
import com.squareup.picasso.Picasso
import java.util.*

/**
 * @author vikas.grover
 * Article Details View Model
 */
class BiggerImageViewModel : BaseViewModel() {

    private var context: Context? = null
    private var binding: ActivityBiggerImageBinding? = null
    var article: Article? = null

    fun int(context: Context, binding: ActivityBiggerImageBinding) {
        this.context = context
        this.binding = binding
    }

    override fun update(p0: Observable?, p1: Any?) {

    }

    fun inflateImages(){
        if(article == null || article?.images.isNullOrEmpty()) return
        val adapter = ImageSliderAdapter(context!!, article?.images!!)
        binding?.viewpager?.adapter = adapter
    }
}