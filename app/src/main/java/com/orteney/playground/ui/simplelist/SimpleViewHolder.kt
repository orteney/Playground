package com.orteney.playground.ui.simplelist

import android.graphics.Typeface
import android.view.MotionEvent
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.dynamicanimation.animation.DynamicAnimation
import androidx.dynamicanimation.animation.SpringAnimation
import androidx.dynamicanimation.animation.SpringForce
import com.orteney.playground.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_simple.*

class SimpleViewHolder(
    override val containerView: View,
    private val listener: SimpleListAdapter.InteractionsListener?
) : androidx.recyclerview.widget.RecyclerView.ViewHolder(containerView),
    LayoutContainer,
    View.OnTouchListener,
    View.OnClickListener {

    /*
    * Flag that used to prevent reset to default elevation when MotionEvent.ACTION_CANCEL appears onTouch stateChangeListener
    * */
    var isInteractionActive = false

    private lateinit var currentModel: SimpleModel

    private val raisedElevation = 60f
    private val minElevation = 0f
    private val maxElevation = 100f
    private val zAnimation: SpringAnimation by lazy { initZAnimation() }

    init {
        cardView.setOnTouchListener(this)
        cardView.setOnClickListener(this)
        initTextSwitcher()
    }

    private fun initTextSwitcher() {
        numberTextSwitcher.apply {
            setFactory {
                TextView(containerView.context).apply {
                    textSize = 14f
                    setTextColor(ContextCompat.getColor(containerView.context, R.color.primary))
                    setTypeface(null, Typeface.BOLD)
                }
            }

            inAnimation = AnimationUtils
                .loadAnimation(containerView.context, android.R.anim.fade_in)
                .apply { duration = 200 }

            outAnimation = AnimationUtils
                .loadAnimation(containerView.context, android.R.anim.fade_out)
                .apply { duration = 200 }
        }
    }

    fun bind(testModel: SimpleModel) {
        currentModel = testModel
        numberTextSwitcher.setCurrentText(testModel.number.toString())
        deleteButton.setOnClickListener { listener?.onDeleteClick(testModel) }
    }

    fun updateNumber(number: Int) {
        numberTextSwitcher.setText(number.toString())
    }

    fun animateToDefaultElevation() {
        zAnimation.animateToFinalPosition(0f)
    }

    fun animateToRiseElevation() {
        zAnimation.animateToFinalPosition(raisedElevation)
    }

    override fun onClick(view: View) {
        listener?.onClick(currentModel)
    }

    override fun onTouch(view: View, event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                animateToRiseElevation()
            }

            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                if (!isInteractionActive) {
                    animateToDefaultElevation()
                }
            }
        }

        return false
    }

    private fun initZAnimation(): SpringAnimation {
        return SpringAnimation(cardView, DynamicAnimation.TRANSLATION_Z)
            .setMinValue(minElevation)
            .setMaxValue(maxElevation)
            .setSpring(
                SpringForce()
                    .setDampingRatio(SpringForce.DAMPING_RATIO_HIGH_BOUNCY)
                    .setStiffness(SpringForce.STIFFNESS_LOW)
            )
    }
}