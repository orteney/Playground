package com.example.testapplication.modules.expandableText

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.testapplication.R
import kotlinx.android.synthetic.main.activity_expandable_text.*
import org.jetbrains.anko.sdk19.listeners.onClick

class ExpandableTextActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expandable_text)

        initViews()
    }

    private fun initViews() {
        shortTextButton.onClick {
            expandableTextView.text = "Я у мамы короткий текст :("
        }

        longTextButton.onClick {
            expandableTextView.text = """
                |    Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
                |
                |    Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.
                |
                |    Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.
                |
                |    Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
                """.trimMargin()
        }
    }
}