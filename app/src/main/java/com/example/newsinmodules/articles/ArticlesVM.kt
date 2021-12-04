package com.example.newsinmodules.articles

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newsinmodules.network.NewsAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class ArticlesVM @Inject constructor(
    private val newsApi: NewsAPI
) : ViewModel() {

    private var _articles = MutableLiveData<List<Article>>()
    val articles: LiveData<List<Article>>
        get() = _articles
    
    private var _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error
    
    fun getArticles() {
        newsApi.getArticles().enqueue(object : Callback<ArticlesResponse> {
            override fun onResponse(
                call: Call<ArticlesResponse>,
                response: Response<ArticlesResponse>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    response.body()?.articles?.let { articles ->
                        _articles.value = articles
                    }
                }
            }

            override fun onFailure(call: Call<ArticlesResponse>, t: Throwable) {
                // TODO: 07.11.2021 Вынести в ресурсы с исп. интерфейса 
                _error.value = "Что-то пошло не так..."
            }
        })
    }
}
