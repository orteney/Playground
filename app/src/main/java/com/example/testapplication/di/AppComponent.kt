package com.example.testapplication.di

import android.app.Application
import android.content.Context
import com.example.testapplication.common.viper.router.BaseAppRouter
import com.example.testapplication.di.module.AppModule
import com.example.testapplication.di.module.NavigationModule
import dagger.Component
import ru.terrakok.cicerone.NavigatorHolder
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class, NavigationModule::class))
interface AppComponent {

    fun provideApplication(): Application
    fun provideContext(): Context

    fun provideNavigationHolder(): NavigatorHolder
    fun provideBaseAppRouter(): BaseAppRouter

}
