package com.example.newsinmodules

import android.app.Application
import com.example.newsinmodules.articles.ArticlesDeps
import com.example.newsinmodules.network.NetworkModule
import com.example.newsinmodules.network.NewsAPI
import dagger.BindsInstance
import dagger.Component

@Component(modules = [NetworkModule::class])
@AppScope
interface AppComponent : ArticlesDeps {

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance application: Application
        ): AppComponent
    }

    override fun api(): NewsAPI
}
