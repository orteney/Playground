package com.example.testapplication.ui.bird

import android.os.Bundle
import android.support.graphics.drawable.AnimatedVectorDrawableCompat
import android.view.View
import com.example.testapplication.R
import com.example.testapplication.ui.common.BaseFragment
import kotlinx.android.synthetic.main.fragment_bird.*
import org.jetbrains.anko.sdk19.listeners.onClick

class BirdFragment : BaseFragment() {

    override val layoutRes: Int = R.layout.fragment_bird

    private val expandAnim: AnimatedVectorDrawableCompat by lazy {
        AnimatedVectorDrawableCompat.create(context!!, R.drawable.avd_checkable_expandcollapse_collapsed_to_expanded)!!
    }

    private val collapseAnim: AnimatedVectorDrawableCompat by lazy {
        AnimatedVectorDrawableCompat.create(context!!, R.drawable.avd_checkable_expandcollapse_expanded_to_collapsed)!!
    }

    private var isExpanded = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        birdImageView.setImageResource(R.drawable.vd_checkable_expandcollapse_collapsed)
        birdImageView.onClick {
            isExpanded = !isExpanded
            val currentDrawable = if (isExpanded) expandAnim else collapseAnim
            birdImageView.setImageDrawable(currentDrawable)
            currentDrawable.start()
        }
    }
}
