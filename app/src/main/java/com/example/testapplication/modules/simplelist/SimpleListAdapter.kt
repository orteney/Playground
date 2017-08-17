package com.example.testapplication.modules.simplelist

import android.support.animation.DynamicAnimation
import android.support.animation.SpringAnimation
import android.support.animation.SpringForce
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import com.example.testapplication.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_simple.*
import org.jetbrains.anko.sdk19.listeners.onClick
import java.util.*

class SimpleListAdapter(private val listener: InteractionsListener? = null) : RecyclerView.Adapter<SimpleListAdapter.ViewHolder>(),
                                                                              ItemTouchHelperAdapter {

    private val data = ArrayList<SimpleModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_simple, parent, false)
        return ViewHolder(view, listener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount() = data.size

    fun setData(data: List<SimpleModel>) {
        val diffUtilCallback = SimpleDiffCallback(this.data, data)
        val diffResult = DiffUtil.calculateDiff(diffUtilCallback)

        this.data.clear()
        this.data.addAll(data)

        diffResult.dispatchUpdatesTo(this)
    }

    override fun onItemDismiss(position: Int) {
        listener?.onItemSwiped(position)
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int) {
        listener?.onItemMoved(fromPosition, toPosition)
    }

    override fun onStartInteractions(viewHolder: RecyclerView.ViewHolder) {
        if (viewHolder is ViewHolder) {
            viewHolder.isInteractionActive = true
            viewHolder.animateToActiveElevation()
        }
    }

    override fun onCompleteInteractions(viewHolder: RecyclerView.ViewHolder) {
        if (viewHolder is ViewHolder) {
            viewHolder.isInteractionActive = false
            viewHolder.animateToDefaultElevation()
        }
    }

    class ViewHolder(
        override val containerView: View,
        private val listener: InteractionsListener?) : RecyclerView.ViewHolder(containerView),
                                                       LayoutContainer,
                                                       View.OnTouchListener,
                                                       View.OnClickListener {

        /*
        * Flag that used to prevent reset to default elevation when MotionEvent.ACTION_CANCEL appears onTouch listener
        * */
        var isInteractionActive = false

        private lateinit var currentModel: SimpleModel

        private val defaultElevation = 4f
        private val raisedElevation = 60f
        private val minElevation = 1f
        private val maxElevation = 100f
        private val zAnimation: SpringAnimation by lazy { initZAnimation() }

        init {
            cardView.setOnTouchListener(this)
            cardView.setOnClickListener(this)
        }

        fun bind(testModel: SimpleModel) {
            currentModel = testModel
            idTextView.text = testModel.id
            deleteButton.onClick { listener?.onDeleteClick(testModel) }
        }

        fun animateToDefaultElevation() {
            zAnimation.animateToFinalPosition(defaultElevation)
        }

        fun animateToActiveElevation() {
            zAnimation.animateToFinalPosition(raisedElevation)
        }

        override fun onClick(view: View) {
            listener?.onClick(currentModel)
        }

        override fun onTouch(view: View, event: MotionEvent): Boolean {
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    animateToActiveElevation()
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
                .setStartValue(defaultElevation)
                .setSpring(
                    SpringForce()
                        .setDampingRatio(SpringForce.DAMPING_RATIO_HIGH_BOUNCY)
                        .setStiffness(SpringForce.STIFFNESS_LOW)
                )
        }
    }

    interface InteractionsListener {
        fun onClick(model: SimpleModel)
        fun onDeleteClick(model: SimpleModel)
        fun onItemSwiped(position: Int)
        fun onItemMoved(fromPosition: Int, toPosition: Int)
    }
}
