package com.example.newsinmodules.articles

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsinmodules.databinding.ArticleItemBinding

class ArticlesAdapter(
    private val articles: List<Article>,
    private val listener: ArticleClickListener
) : RecyclerView.Adapter<ArticleVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleVH {
        val binding = ArticleItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ArticleVH(binding)
    }

    override fun onBindViewHolder(holder: ArticleVH, position: Int) {
        holder.bind(articles[position], listener)
    }

    override fun getItemCount(): Int {
        return articles.size
    }
}

class ArticleVH(
    private val binding: ArticleItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(article: Article, listener: ArticleClickListener) {
        with(binding) {
            tvTitle.text = article.title
            tvDescription.text = article.description
            Glide.with(root.context)
                .load(article.urlToImage)
                .into(ivImage)
            root.setOnClickListener { listener.onArticleClick(article) }
        }
    }
}
