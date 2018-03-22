package com.example.testapplication.modules.textviews

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.testapplication.R
import com.google.android.flexbox.FlexboxLayout
import kotlinx.android.synthetic.main.activity_text_views.*
import org.jetbrains.anko.button
import org.jetbrains.anko.sdk19.listeners.onClick

class TextViewsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_text_views)

        val list = listOf<Int>()

        list.joinToString(separator = " ") { (it+1).toString() }

        initViews()
    }

    private fun initViews() {
        addButton.onClick {
            bottomLayout.button {
                text = "Delete"
                onClick { bottomLayout.removeView(this) }
            }.layoutParams = FlexboxLayout.LayoutParams(FlexboxLayout.LayoutParams.MATCH_PARENT, FlexboxLayout.LayoutParams.WRAP_CONTENT)
        }
    }
}