package com.example.newsinmodules

import android.app.Application
import androidx.lifecycle.ViewModel
import com.example.newsinmodules.articles.ArticlesDeps
import com.example.newsinmodules.articles.ArticlesVM
import com.example.newsinmodules.network.NetworkModule
import com.example.newsinmodules.network.NewsAPI
import dagger.Binds
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.multibindings.IntoMap

@Component(modules = [NetworkModule::class, AppBindsModule::class])
@AppScope
interface AppComponent : ArticlesDeps {

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance application: Application
        ): AppComponent
    }

    override fun api(): NewsAPI

    override val factory: MultiViewModelFactory
}

@Module
interface AppBindsModule {

    @Binds
    @[IntoMap ViewModelKey(ArticlesVM::class)]
    fun provideArticlesVM(articlesVM: ArticlesVM): ViewModel
}
