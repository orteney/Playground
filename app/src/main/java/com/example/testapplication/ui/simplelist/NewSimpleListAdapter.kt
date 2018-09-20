package com.example.testapplication.ui.simplelist

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.testapplication.R

class NewSimpleListAdapter(private val listener: SimpleListAdapter.InteractionsListener? = null) :
    ListAdapter<SimpleModel, SimpleViewHolder>(SimpleDiffItemCallback()),
    ItemTouchHelperAdapter {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_simple, parent, false)
        return SimpleViewHolder(view, listener)
    }

    override fun onBindViewHolder(
        holder: SimpleViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isEmpty()) return super.onBindViewHolder(holder, position, payloads)

        val newNumber = payloads[0] as? Int ?: return
        holder.updateNumber(newNumber)
    }

    override fun onBindViewHolder(holder: SimpleViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onItemDismiss(position: Int) {
        listener?.onItemSwiped(position)
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int) {
        listener?.onItemMoved(fromPosition, toPosition)
    }

    override fun onStartInteractions(viewHolder: RecyclerView.ViewHolder) {
        if (viewHolder is SimpleViewHolder) {
            viewHolder.animateToRiseElevation()
            viewHolder.isInteractionActive = true
        }
    }

    override fun onCompleteInteractions(viewHolder: RecyclerView.ViewHolder) {
        if (viewHolder is SimpleViewHolder) {
            viewHolder.animateToDefaultElevation()
            viewHolder.isInteractionActive = false
        }
    }
}