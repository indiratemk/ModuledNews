package com.example.newsinmodules.articles

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsinmodules.NewsApp
import com.example.newsinmodules.databinding.MainActivityBinding
import com.example.newsinmodules.details.ArticleDetailsActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: MainActivityBinding
    private lateinit var viewModel: ArticlesVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val articlesComponent = DaggerArticlesComponent.factory()
            .create(deps = (application as NewsApp).appComponent)
        articlesComponent.inject(this)
        viewModel = articlesComponent.factory.create(ArticlesVM::class.java)

        viewModel.articles.observe(this, { articles ->
            val articlesAdapter = ArticlesAdapter(
                articles,
                object : ArticleClickListener {
                    override fun onArticleClick(article: Article) {
                        ArticleDetailsActivity.startActivity(
                            this@MainActivity,
                            article
                        )
                    }
                }
            )
            binding.rvNews.apply {
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = articlesAdapter
                setHasFixedSize(true)
            }
        })

        viewModel.error.observe(this, { error ->
            Toast.makeText(this@MainActivity, error, Toast.LENGTH_SHORT).show()
        })

        viewModel.getArticles()
    }
}
