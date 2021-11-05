package com.example.newsinmodules.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.newsinmodules.articles.Article
import com.example.newsinmodules.databinding.ArticleDetailsActivityBinding

class ArticleDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ArticleDetailsActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ArticleDetailsActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val article = intent.getSerializableExtra(EXTRA_ARTICLE) as Article

        with(binding) {
            tvTitle.text = article.title
            tvDescription.text = article.description
            tvAuthor.text = article.author
            Glide.with(root.context)
                .load(article.urlToImage)
                .into(ivImage)
        }
    }

    companion object {

        private const val EXTRA_ARTICLE = "EXTRA_ARTICLE"

        fun startActivity(context: Context, article: Article) {
            val intent = Intent(context, ArticleDetailsActivity::class.java)
            intent.putExtra(EXTRA_ARTICLE, article)
            context.startActivity(intent)
        }
    }
}
