package com.orteney.playground.ui.bird

import android.os.Bundle
import android.view.View
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat
import com.orteney.playground.R
import com.orteney.playground.ui.common.BaseFragment
import kotlinx.android.synthetic.main.fragment_bird.*

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
        birdImageView.setOnClickListener {
            isExpanded = !isExpanded
            val currentDrawable = if (isExpanded) expandAnim else collapseAnim
            birdImageView.setImageDrawable(currentDrawable)
            currentDrawable.start()
        }
    }
}
