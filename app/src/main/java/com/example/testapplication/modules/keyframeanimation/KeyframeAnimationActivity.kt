package com.example.testapplication.modules.keyframeanimation

import android.os.Bundle
import android.support.constraint.ConstraintSet
import android.support.transition.ChangeBounds
import android.support.transition.TransitionManager
import android.support.v7.app.AppCompatActivity
import android.view.animation.AnticipateOvershootInterpolator
import com.example.testapplication.R
import kotlinx.android.synthetic.main.activity_keyframe_animation_init.*
import org.jetbrains.anko.sdk19.listeners.onClick

class KeyframeAnimationActivity : AppCompatActivity() {

    private var isDisplayed = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_keyframe_animation_init)

        initViews()
    }

    private fun initViews() {
        toolbar.title = "Keyframe Animation"

        backgroundImageView.onClick {
            if (isDisplayed) hide() else show()
        }
    }

    private fun show() {
        val constraintSet = ConstraintSet()
        constraintSet.clone(this, R.layout.activity_keyframe_animation_loaded)

        val transition = ChangeBounds()
        transition.interpolator = AnticipateOvershootInterpolator(1.0f)
        transition.duration = 1200

        TransitionManager.beginDelayedTransition(rootLayout, transition)

        constraintSet.applyTo(rootLayout)

        isDisplayed = true
    }

    private fun hide() {
        val constraintSet = ConstraintSet()
        constraintSet.clone(this, R.layout.activity_keyframe_animation_init)

        val transition = ChangeBounds()
        transition.interpolator = AnticipateOvershootInterpolator(1.0f)
        transition.duration = 1200

        TransitionManager.beginDelayedTransition(rootLayout, transition)

        constraintSet.applyTo(rootLayout)

        isDisplayed = false
    }
}
