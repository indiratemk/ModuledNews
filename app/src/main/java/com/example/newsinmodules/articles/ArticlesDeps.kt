package com.example.newsinmodules.articles

import com.example.newsinmodules.network.NewsAPI

interface ArticlesDeps {

    fun api(): NewsAPI
}
