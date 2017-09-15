package com.example.testapplication.modules.expandablelist

import android.content.Context
import android.support.graphics.drawable.AnimatedVectorDrawableCompat
import android.support.v7.widget.LinearLayoutManager
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.example.testapplication.R
import kotlinx.android.synthetic.main.view_expandable.view.*
import net.cachapa.expandablelayout.ExpandableLayout
import org.jetbrains.anko.sdk19.listeners.onClick


class ExpandableView : LinearLayout, ExpandableLayout.OnExpansionUpdateListener, ParcelAdapter.InteractionsListener {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    var stateChangeListener: OnStateChangeListener? = null
    var parcelInteractionsListener: ParcelInteractionsListener? = null

    private var parcelsCount: Int = 0

    private val adapter: ParcelAdapter

    private val expandAnim: AnimatedVectorDrawableCompat by lazy {
        AnimatedVectorDrawableCompat.create(context, R.drawable.avd_checkable_expandcollapse_collapsed_to_expanded)!!
    }

    private val collapseAnim: AnimatedVectorDrawableCompat by lazy {
        AnimatedVectorDrawableCompat.create(context, R.drawable.avd_checkable_expandcollapse_expanded_to_collapsed)!!
    }

    init {
        LayoutInflater.from(context).inflate(R.layout.view_expandable, this)

        // Init recycler view
        adapter = ParcelAdapter(this)
        containerRecyclerView.layoutManager = LinearLayoutManager(context)
        containerRecyclerView.adapter = adapter

        // Init expandable handler
        updateArrowState(expandableLayout.isExpanded, false)
        expandableLayout.setOnExpansionUpdateListener(this)
        handlerLayout.onClick { toggleExpandable() }
        updateCounter()
    }

    override fun onExpansionUpdate(expansionFraction: Float, state: Int) {
        when (state) {
            ExpandableLayout.State.COLLAPSED -> stateChangeListener?.onExpandStateChanged(false)
            ExpandableLayout.State.EXPANDED -> stateChangeListener?.onExpandStateChanged(true)
        }
    }

    override fun onParcelClick(model: ParcelModel) {
        parcelInteractionsListener?.onParcelClick(model)
    }

    override fun onParcelRemoveClick(model: ParcelModel) {
        parcelInteractionsListener?.onParcelRemoveClick(model)
    }

    fun expand(animated: Boolean = true) {
        expandableLayout.expand(animated)
        updateArrowState(animated)
    }

    fun collapse(animated: Boolean = true) {
        expandableLayout.collapse(animated)
        updateArrowState(animated)
    }

    private fun toggleExpandable() {
        if (canExpand()) {
            val isExpanded = expandableLayout.isExpanded
            expandableLayout.toggle()
            updateArrowState(!isExpanded)
        }
    }

    private fun updateArrowState(isExpanded: Boolean, animated: Boolean = true) {
        if (animated) {
            val currentDrawable = if (isExpanded) expandAnim else collapseAnim
            arrowImageView.setImageDrawable(currentDrawable)
            currentDrawable.start()
        } else {
            arrowImageView.setImageResource(if (isExpanded) R.drawable.vd_checkable_expandcollapse_expanded else R.drawable.vd_checkable_expandcollapse_collapsed)
        }
    }

    fun setTitle(title: String) {
        titleTextView.text = title
    }

    fun setSubtitle(subtitle: String) {
        subtitleTextView.text = subtitle
    }

    fun setParcels(parcels: List<ParcelModel>) {
        adapter.setData(parcels)
        parcelsCount = parcels.count()
        updateCounter()
    }

    private fun canExpand(): Boolean {
        return parcelsCount > 0
    }

    private fun updateCounter() {
        countTextView.text = parcelsCount.toString()
    }

    interface OnStateChangeListener {
        fun onExpandStateChanged(isExpanded: Boolean)
    }

    interface ParcelInteractionsListener {
        fun onParcelClick(model: ParcelModel)
        fun onParcelRemoveClick(model: ParcelModel)
    }
}