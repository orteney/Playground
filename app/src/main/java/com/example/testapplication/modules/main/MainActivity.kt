package com.example.testapplication.modules.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.testapplication.R
import com.example.testapplication.modules.flexboxlist.FlexBoxListActivity
import com.example.testapplication.modules.simplelist.SimpleListActivity
import com.example.testapplication.modules.textarea.TextAreaActivity
import com.example.testapplication.modules.textviews.TextViewsActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.sdk19.listeners.onClick
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
    }

    private fun initViews() {
        showListButton.onClick { startActivity<SimpleListActivity>() }
        showFlexBoxListButton.onClick { startActivity<FlexBoxListActivity>() }
        showTextAreaButton.onClick { startActivity<TextAreaActivity>() }
        showTextViewsButton.onClick { startActivity<TextViewsActivity>() }
    }
}