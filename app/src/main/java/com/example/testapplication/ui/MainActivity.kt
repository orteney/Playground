package com.example.testapplication.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.testapplication.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.AppTheme)
        setContentView(R.layout.activity_main)
    }
}
