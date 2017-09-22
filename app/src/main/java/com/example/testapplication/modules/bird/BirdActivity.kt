package com.example.testapplication.modules.bird

import android.os.Bundle
import android.support.graphics.drawable.AnimatedVectorDrawableCompat
import android.support.v7.app.AppCompatActivity
import com.example.testapplication.R
import kotlinx.android.synthetic.main.activity_bird.*
import org.jetbrains.anko.sdk19.listeners.onClick

class BirdActivity : AppCompatActivity() {

    private val expandAnim: AnimatedVectorDrawableCompat by lazy {
        AnimatedVectorDrawableCompat.create(this, R.drawable.avd_checkable_expandcollapse_collapsed_to_expanded)!!
    }

    private val collapseAnim: AnimatedVectorDrawableCompat by lazy {
        AnimatedVectorDrawableCompat.create(this, R.drawable.avd_checkable_expandcollapse_expanded_to_collapsed)!!
    }

    private var isExpanded = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bird)

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
