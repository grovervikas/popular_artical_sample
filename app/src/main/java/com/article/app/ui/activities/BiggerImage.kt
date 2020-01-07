package com.article.app.ui.activities

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.article.app.BaseViewModelActivity
import com.article.app.Const
import com.article.app.R
import com.article.app.databinding.ActivityBiggerImageBinding
import com.article.app.model.Article
import com.article.app.vm.BiggerImageViewModel
import kotlinx.android.synthetic.main.activity_article_list.*

/**
 * @author Vikas.grover
 * Article Image
 */
class BiggerImage : BaseViewModelActivity<BiggerImageViewModel>() {

    private val biggerImageViewModel = BiggerImageViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)
        val binding = DataBindingUtil.setContentView<ViewDataBinding>(this, R.layout.activity_bigger_image) as ActivityBiggerImageBinding
        binding.biggerImageViewModel = biggerImageViewModel
        biggerImageViewModel.int(this, binding)
        if(intent == null || !intent.hasExtra(Const.KEY_INTENT_PRODUCT_DETAILS)) return
        biggerImageViewModel.article = intent.getSerializableExtra(Const.KEY_INTENT_PRODUCT_DETAILS) as Article
        biggerImageViewModel.inflateImages()
    }
}