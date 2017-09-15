package com.example.testapplication.modules.textboxes

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.example.testapplication.R
import kotlinx.android.synthetic.main.activity_text_boxes.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.sdk19.listeners.onClick
import org.jetbrains.anko.singleTop

class TextBoxesActivity : AppCompatActivity() {

    private var isLightTheme = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if(savedInstanceState == null) {
            isLightTheme = intent.getBooleanExtra("isLightTheme", true)
        }

        setTheme(if (isLightTheme) R.style.AppTheme else R.style.DarkTheme)

        setContentView(R.layout.activity_text_boxes)

        initViews()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_theme, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_change_theme -> {
                startActivity(intentFor<TextBoxesActivity>("isLightTheme" to !isLightTheme).singleTop())
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun initViews() {
        showErrorButton.onClick { showErrors() }
        hideErrorButton.onClick { hideErrors() }
        secondTextBox
    }

    private fun showErrors() {
        firstTextBox.setError("Я у мамы ошибка!")
        secondTextBox.setError("Я у мамы ошибка!")
    }

    private fun hideErrors() {
        firstTextBox.removeError()
        secondTextBox.removeError()
    }
}
