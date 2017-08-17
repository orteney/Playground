package com.example.testapplication.modules.textarea

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.testapplication.R
import kotlinx.android.synthetic.main.activity_text_area.*
import org.jetbrains.anko.sdk19.listeners.onClick

class TextAreaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_text_area)

        initViews()
    }

    private fun initViews() {

        showErrorButton.onClick {
            val error = "Some error message"

            defaultInputLayout.error = error
            textAreaInputLayout.error = error
        }

    }

}