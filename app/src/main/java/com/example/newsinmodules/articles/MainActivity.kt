package com.example.newsinmodules.articles

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsinmodules.NewsApp
import com.example.newsinmodules.databinding.MainActivityBinding
import com.example.newsinmodules.details.ArticleDetailsActivity
import com.example.newsinmodules.network.NewsAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var newsAPI: NewsAPI
    
    private lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        (application as NewsApp).appComponent.inject(this)

        newsAPI.getArticles().enqueue(object : Callback<ArticlesResponse> {
            override fun onResponse(
                call: Call<ArticlesResponse>,
                response: Response<ArticlesResponse>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    response.body()?.articles?.let { articles ->
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
                    }
                }
            }

            override fun onFailure(call: Call<ArticlesResponse>, t: Throwable) {
                Toast.makeText(
                    this@MainActivity,
                    "Что-то пошло не так =(",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }
}
