package com.article.app.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.article.app.R
import com.article.app.databinding.ArticleItemBinding
import com.article.app.model.Article
import com.article.app.vm.ArticleItemViewModel


/**
 * @author vikas.grover
 * Article List Adapter : to inflate article items row by row
 */
class ArticleListAdapter(private val items: List<Article>, private var articleAdapterListener: ArticleAdapterListener ) :
    RecyclerView.Adapter<ArticleListAdapter.ArticleViewHolder>(), ArticleItemViewModel.ArticleItemListener {


    @NonNull
    override fun onCreateViewHolder(@NonNull parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.article_item, parent, false) as ArticleItemBinding
        return ArticleViewHolder(binding)
    }

    override fun onBindViewHolder(@NonNull holder: ArticleViewHolder, position: Int) {
        val articleItemViewModel = ArticleItemViewModel()
        articleItemViewModel.onDataAvailable(items[position], this)
        holder.binding.articleItemViewModel = articleItemViewModel
    }

    override fun getItemCount(): Int = items.size

    public inner class ArticleViewHolder(val binding: ArticleItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun handleItemClick(article: Article) {
        articleAdapterListener.adapterItemClick(article)
    }

    interface ArticleAdapterListener {
        fun adapterItemClick(article: Article)
    }

}