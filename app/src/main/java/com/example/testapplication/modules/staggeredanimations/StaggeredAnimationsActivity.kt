package com.example.testapplication.modules.staggeredanimations

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.testapplication.R
import kotlinx.android.synthetic.main.activity_staggered_animations.*
import org.jetbrains.anko.sdk19.listeners.onClick

class StaggeredAnimationsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_staggered_animations)
        initViews()
    }

    private fun initViews() {
        hideButton.onClick { group.hide(true) }
        showButton.onClick { group.show(false) }
    }
}