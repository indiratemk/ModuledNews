package com.example.newsinmodules.network

import com.example.newsinmodules.AppScope
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
object NetworkModule {

    private const val BASE_URL = "https://newsapi.org/v2/"

    @Provides
    @AppScope
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @AppScope
    fun provideNewsAPI(retrofit: Retrofit): NewsAPI = retrofit.create(NewsAPI::class.java)
}
