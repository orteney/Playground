package com.example.testapplication.di.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides


@Module
class AppModule(val application: Application) {

    @Provides
    fun provideApplication(): Application {
        return application
    }

    @Provides
    fun provideContext(): Context {
        return application
    }

}