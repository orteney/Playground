package com.example.testapplication.ui.staggeredanimations

import android.os.Bundle
import android.view.View
import com.example.testapplication.R
import com.example.testapplication.ui.common.BaseFragment
import kotlinx.android.synthetic.main.fragment_staggered_animations.*
import org.jetbrains.anko.sdk19.listeners.onClick

class StaggeredAnimationFragment : BaseFragment() {

    override val layoutRes: Int = R.layout.fragment_staggered_animations

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        hideButton.onClick { group.hide(true) }
        showButton.onClick { group.show(false) }
    }
}