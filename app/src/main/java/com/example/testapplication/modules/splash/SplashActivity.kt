package com.example.testapplication.modules.splash

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.testapplication.modules.main.MainActivity
import org.jetbrains.anko.startActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startActivity<MainActivity>()
    }

}