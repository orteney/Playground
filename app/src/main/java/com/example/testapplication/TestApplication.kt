package com.example.testapplication

import android.app.Application
import com.example.testapplication.di.AppComponent
import com.example.testapplication.di.DaggerAppComponent
import com.example.testapplication.di.module.AppModule


class TestApplication : Application() {

    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }
}
