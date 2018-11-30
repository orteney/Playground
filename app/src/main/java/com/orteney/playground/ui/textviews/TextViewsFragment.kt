package com.orteney.playground.ui.textviews

import android.os.Bundle
import android.view.View
import com.google.android.flexbox.FlexboxLayout
import com.google.android.material.button.MaterialButton
import com.orteney.playground.R
import com.orteney.playground.ui.common.BaseFragment
import kotlinx.android.synthetic.main.fragment_text_views.*

class TextViewsFragment : BaseFragment() {

    override val layoutRes: Int = R.layout.fragment_text_views

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        addButton.setOnClickListener {
            val button = MaterialButton(context).apply {
                text = "Delete"
                setOnClickListener { bottomLayout.removeView(this) }
                layoutParams = FlexboxLayout.LayoutParams(
                    FlexboxLayout.LayoutParams.MATCH_PARENT,
                    FlexboxLayout.LayoutParams.WRAP_CONTENT
                )
            }

            bottomLayout.addView(button)
        }
    }
}