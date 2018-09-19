package com.example.testapplication.ui.expandableText

import android.os.Bundle
import android.view.View
import com.example.testapplication.R
import com.example.testapplication.ui.common.BaseFragment
import kotlinx.android.synthetic.main.fragment_expandable_text.*
import org.jetbrains.anko.sdk19.listeners.onClick

class ExpandableTextFragment : BaseFragment() {

    override val layoutRes: Int = R.layout.fragment_expandable_text

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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