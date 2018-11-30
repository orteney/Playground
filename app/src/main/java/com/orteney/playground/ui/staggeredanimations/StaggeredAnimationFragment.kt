package com.orteney.playground.ui.staggeredanimations

import android.os.Bundle
import android.view.View
import com.orteney.playground.R
import com.orteney.playground.ui.common.BaseFragment
import kotlinx.android.synthetic.main.fragment_staggered_animations.*

class StaggeredAnimationFragment : BaseFragment() {

    override val layoutRes: Int = R.layout.fragment_staggered_animations

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        hideButton.setOnClickListener { group.hide(true) }
        showButton.setOnClickListener { group.show(false) }
    }
}