package com.example.newsinmodules.articles

import java.io.Serializable

data class Article(
    val author: String? = null,
    val title: String? = null,
    val description: String? = null,
    val urlToImage: String? = null
) : Serializable
