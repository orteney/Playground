package com.orteney.playground.extensions

import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.os.Parcelable
import android.view.View
import android.view.ViewAnimationUtils
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import kotlinx.android.parcel.Parcelize

fun View.registerCircularRevealAnimation(revealSettings: RevealAnimationSetting, startColor: Int, endColor: Int) {
    addOnLayoutChangeListener(object : View.OnLayoutChangeListener {
        override fun onLayoutChange(v: View?, left: Int, top: Int, right: Int, bottom: Int, oldLeft: Int, oldTop: Int, oldRight: Int, oldBottom: Int) {
            removeOnLayoutChangeListener(this)
            val cx = revealSettings.centerX
            val cy = revealSettings.centerY
            val width = revealSettings.width
            val height = revealSettings.height
            val duration = context.resources.getInteger(android.R.integer.config_mediumAnimTime).toLong()

            //Simply use the diagonal of the view
            val finalRadius = Math.sqrt((width * width + height * height).toDouble()).toFloat()
            val anim = ViewAnimationUtils.createCircularReveal(v, cx, cy, 0f, finalRadius)
                .setDuration(duration)
            anim.interpolator = FastOutSlowInInterpolator()
            anim.start()
            startColorAnimation(startColor, endColor, duration)
        }
    })
}

fun View.startColorAnimation(startColor: Int, endColor: Int, duration: Long) {
    with(ValueAnimator()) {
        setIntValues(startColor, endColor)
        setEvaluator(ArgbEvaluator())
        addUpdateListener {
            setBackgroundColor(this.animatedValue as Int)
        }
        setDuration(duration)
        start()
    }
}

@Parcelize
class RevealAnimationSetting(
    val centerX: Int,
    val centerY: Int,
    val width: Int,
    val height: Int
) : Parcelable