package com.example.newsinmodules.network

import com.example.newsinmodules.BuildConfig
import com.example.newsinmodules.articles.ArticlesResponse
import retrofit2.Call
import retrofit2.http.GET

interface NewsAPI {

    @GET("top-headlines?country=us&apiKey=${BuildConfig.API_KEY}")
    fun getArticles(): Call<ArticlesResponse>
}
