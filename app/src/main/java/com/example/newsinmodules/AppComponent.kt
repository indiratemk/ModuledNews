package com.example.newsinmodules

import com.example.newsinmodules.articles.MainActivity
import com.example.newsinmodules.network.NetworkModule
import dagger.Component
import javax.inject.Singleton

@Component(modules = [NetworkModule::class])
@Singleton
interface AppComponent {

    fun inject(activity: MainActivity)
}
