package com.example.newsinmodules.articles

import dagger.Component

@Component(dependencies = [ArticlesDeps::class])
@ArticlesScope
interface ArticlesComponent {

    @Component.Factory
    interface Factory {
        fun create(deps: ArticlesDeps): ArticlesComponent
    }

    fun inject(activity: MainActivity)
}
