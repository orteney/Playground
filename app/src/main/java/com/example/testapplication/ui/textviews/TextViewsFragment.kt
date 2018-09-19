package com.example.testapplication.ui.textviews

import android.os.Bundle
import android.view.View
import com.example.testapplication.R
import com.example.testapplication.ui.common.BaseFragment
import com.google.android.flexbox.FlexboxLayout
import kotlinx.android.synthetic.main.fragment_text_views.*
import org.jetbrains.anko.button
import org.jetbrains.anko.sdk19.listeners.onClick

class TextViewsFragment : BaseFragment() {

    override val layoutRes: Int = R.layout.fragment_text_views

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        addButton.onClick {
            bottomLayout.button {
                text = "Delete"
                onClick { bottomLayout.removeView(this) }
            }.layoutParams =
                FlexboxLayout.LayoutParams(FlexboxLayout.LayoutParams.MATCH_PARENT, FlexboxLayout.LayoutParams.WRAP_CONTENT)
        }
    }
}