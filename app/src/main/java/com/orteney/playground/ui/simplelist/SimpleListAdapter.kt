package com.orteney.playground.ui.simplelist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.orteney.playground.R

class SimpleListAdapter(
    private val listener: InteractionsListener? = null
) : ListAdapter<SimpleModel, SimpleViewHolder>(SimpleDiffItemCallback()),
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
            viewHolder.onStartInteraction()
        }
    }

    override fun onCompleteInteractions(viewHolder: RecyclerView.ViewHolder) {
        if (viewHolder is SimpleViewHolder) {
            viewHolder.onFinishInteraction()
        }
    }

    interface InteractionsListener {
        fun onClick(model: SimpleModel)
        fun onDeleteClick(model: SimpleModel)
        fun onItemSwiped(position: Int)
        fun onItemMoved(fromPosition: Int, toPosition: Int)
    }
}