package com.example.testapplication.modules.expandableText

import android.animation.Animator
import android.animation.ValueAnimator
import android.content.Context
import android.support.graphics.drawable.AnimatedVectorDrawableCompat
import android.support.v4.view.animation.FastOutSlowInInterpolator
import android.text.TextUtils
import android.util.AttributeSet
import android.view.View
import android.widget.TextView
import com.example.testapplication.R
import org.jetbrains.anko.sdk19.listeners.onClick


class ExpandableTextView : TextView {

    private var bottomPadding = 0

    private val expandedLinesCount = Int.MAX_VALUE
    private val collapsedLinesCount = 2
    private val animationDurationMillis = 300L

    private val expandAnim: AnimatedVectorDrawableCompat by lazy {
        AnimatedVectorDrawableCompat.create(context, R.drawable.avd_checkable_expandcollapse_collapsed_to_expanded)!!
    }

    private val collapseAnim: AnimatedVectorDrawableCompat by lazy {
        AnimatedVectorDrawableCompat.create(context, R.drawable.avd_checkable_expandcollapse_expanded_to_collapsed)!!
    }

    private var descriptionCollapsedHeight: Int = 0

    private val isEllipsized get() = lineCount > collapsedLinesCount

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        obtainAttributes(attrs)
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        obtainAttributes(attrs)
    }

    init {
        ellipsize = TextUtils.TruncateAt.END
        maxLines = collapsedLinesCount
        isClickable = false
        onClick { toggleExpansion() }
    }

    private fun obtainAttributes(attrs: AttributeSet?) {
        val attributes = intArrayOf(android.R.attr.paddingBottom)
        val arr = context.obtainStyledAttributes(attrs, attributes)
        bottomPadding = arr.getDimensionPixelOffset(0, 0)
        arr.recycle()
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

                override fun onAnimationCancel(animation: Animator) {}

                override fun onAnimationRepeat(animation: Animator) {}
            })

            interpolator = FastOutSlowInInterpolator()

            duration = animationDurationMillis

            start()
        }
    }

    override fun setText(text: CharSequence?, type: BufferType?) {
        maxLines = expandedLinesCount

        super.setText(text, type)

        if (isEllipsized) {
            maxLines = collapsedLinesCount
            updateArrowState(false, false)
            isClickable = true
            updateBottomPadding(true)
        } else {
            isClickable = false
            setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
            updateBottomPadding(false)
        }
    }

    private fun updateBottomPadding(isBottomDrawableVisible: Boolean) {
        val newPaddingBottom = if (!isBottomDrawableVisible) bottomPadding else bottomPadding - compoundDrawablePadding

        setPadding(paddingLeft, paddingTop, paddingRight, newPaddingBottom)
    }
}