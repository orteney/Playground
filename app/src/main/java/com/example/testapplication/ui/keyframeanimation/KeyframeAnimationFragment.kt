package com.example.testapplication.ui.keyframeanimation

import android.os.Bundle
import android.support.constraint.ConstraintSet
import android.support.transition.ChangeBounds
import android.support.transition.TransitionManager
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnticipateOvershootInterpolator
import com.example.testapplication.R
import kotlinx.android.synthetic.main.fragment_keyframe_animation_init.*
import org.jetbrains.anko.sdk19.listeners.onClick

class KeyframeAnimationFragment : Fragment() {

    private var isDisplayed = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_keyframe_animation_init, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
        constraintSet.clone(context, R.layout.fragment_keyframe_animation_loaded)

        val transition = ChangeBounds()
        transition.interpolator = AnticipateOvershootInterpolator(1.0f)
        transition.duration = 1200

        TransitionManager.beginDelayedTransition(rootLayout, transition)

        constraintSet.applyTo(rootLayout)

        isDisplayed = true
    }

    private fun hide() {
        val constraintSet = ConstraintSet()
        constraintSet.clone(context, R.layout.fragment_keyframe_animation_init)

        val transition = ChangeBounds()
        transition.interpolator = AnticipateOvershootInterpolator(1.0f)
        transition.duration = 1200

        TransitionManager.beginDelayedTransition(rootLayout, transition)

        constraintSet.applyTo(rootLayout)

        isDisplayed = false
    }
}
