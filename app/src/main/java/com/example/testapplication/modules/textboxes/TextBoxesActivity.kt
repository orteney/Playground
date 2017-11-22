package com.example.testapplication.modules.textboxes

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.testapplication.R
import kotlinx.android.synthetic.main.activity_text_boxes.*
import org.jetbrains.anko.sdk19.listeners.onClick

class TextBoxesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_text_boxes)

        initViews()
    }

    private fun initViews() {
        showErrorButton.onClick { showErrors() }
        hideErrorButton.onClick { hideErrors() }
        secondTextBox
    }

    private fun showErrors() {
        firstTextBox.setError("Я у мамы ошибка!", false)
        secondTextBox.setError("Я у мамы ошибка!", false)
    }

    private fun hideErrors() {
        firstTextBox.removeError()
        secondTextBox.removeError()
    }
}
