package com.orteney.playground.ui.expandableText

import android.animation.Animator
import android.animation.ValueAnimator
import android.content.Context
import android.text.TextUtils
import android.util.AttributeSet
import android.view.View
import android.widget.TextView
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat
import com.orteney.playground.R

class ExpandableTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0) : TextView(context, attrs, defStyleAttr) {

    private val expandedLinesCount = Int.MAX_VALUE
    private val collapsedLinesCount = 2
    private val animationDurationMillis = 300L

    private val expandAnim: AnimatedVectorDrawableCompat by lazy {
        val animateDrawable = AnimatedVectorDrawableCompat.create(context, R.drawable.avd_checkable_expandcollapse_collapsed_to_expanded)
        checkNotNull(animateDrawable) { "AnimatedVectorDrawableCompat resource parsing error" }
    }

    private val collapseAnim: AnimatedVectorDrawableCompat by lazy {
        val animateDrawable = AnimatedVectorDrawableCompat.create(context, R.drawable.avd_checkable_expandcollapse_expanded_to_collapsed)
        checkNotNull(animateDrawable) { "AnimatedVectorDrawableCompat resource parsing error" }
    }

    private val isEllipsized get() = lineCount > collapsedLinesCount

    private var shouldRelayout = true
    private var descriptionCollapsedHeight: Int = 0
    private var attributePadding: Int = 0

    init {
        ellipsize = TextUtils.TruncateAt.END
        maxLines = collapsedLinesCount
        isClickable = false
        setOnClickListener { toggleExpansion() }

        attributePadding = paddingBottom
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        if (!shouldRelayout) return super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        shouldRelayout = false
        maxLines = expandedLinesCount
        isClickable = false
        setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)

        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        if (isEllipsized) {
            maxLines = collapsedLinesCount
            updateArrowState(false, false)
            isClickable = true
            updateBottomPadding(true)
        } else {
            updateBottomPadding(false)
        }

        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun setText(text: CharSequence?, type: BufferType?) {
        shouldRelayout = true
        super.setText(text, type)
    }

    private fun updateArrowState(shouldExpand: Boolean, animated: Boolean = true) {
        if (animated) {
            val currentDrawable = if (shouldExpand) expandAnim else collapseAnim
            setCompoundDrawablesWithIntrinsicBounds(null, null, null, currentDrawable)
            currentDrawable.start()
        } else {
            val drawableResId = if (shouldExpand) R.drawable.vd_checkable_expandcollapse_expanded else R.drawable.vd_checkable_expandcollapse_collapsed
            setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, drawableResId)
        }
    }

    private fun toggleExpansion() {
        val isExpandedNow = maxLines > collapsedLinesCount

        val currentHeight = measuredHeight

        if (!isExpandedNow) {
            descriptionCollapsedHeight = currentHeight
        }

        maxLines = if (!isExpandedNow) expandedLinesCount else collapsedLinesCount

        measure(
            View.MeasureSpec.makeMeasureSpec(measuredWidth, View.MeasureSpec.EXACTLY),
            View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED)
        )

        val newHeight = if (!isExpandedNow) measuredHeight else descriptionCollapsedHeight

        ValueAnimator.ofInt(height, newHeight).apply {
            addUpdateListener { animator ->
                height = animator.animatedValue as Int
                requestLayout()
            }

            addListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(animation: Animator) {
                    updateArrowState(!isExpandedNow)
                    isClickable = false
                }

                override fun onAnimationEnd(animation: Animator) {
                    maxLines = if (!isExpandedNow) expandedLinesCount else collapsedLinesCount
                    isClickable = true
                }

                override fun onAnimationCancel(animation: Animator) = Unit
                override fun onAnimationRepeat(animation: Animator) = Unit
            })

            interpolator = FastOutSlowInInterpolator()
            duration = animationDurationMillis

            start()
        }
    }

    private fun updateBottomPadding(isBottomDrawableVisible: Boolean) {
        val newPaddingBottom = if (!isBottomDrawableVisible) attributePadding else attributePadding - compoundDrawablePadding
        setPadding(paddingLeft, paddingTop, paddingRight, newPaddingBottom)
    }
}