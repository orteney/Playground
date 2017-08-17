package com.example.testapplication.di.module

import com.example.testapplication.common.viper.router.BaseAppRouter
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import javax.inject.Singleton


@Module
class NavigationModule {
    private val cicerone: Cicerone<BaseAppRouter> = Cicerone.create(BaseAppRouter())

    @Provides
    @Singleton
    fun provideRouter(): BaseAppRouter = cicerone.router

    @Provides
    @Singleton
    fun provideNavigatorHolder(): NavigatorHolder = cicerone.navigatorHolder
}
