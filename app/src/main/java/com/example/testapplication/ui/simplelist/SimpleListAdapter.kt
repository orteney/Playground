package com.example.testapplication.ui.simplelist

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.testapplication.R

class SimpleListAdapter(private val listener: InteractionsListener? = null) : RecyclerView.Adapter<SimpleViewHolder>(),
                                                                              ItemTouchHelperAdapter {

    private var data = emptyList<SimpleModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_simple, parent, false)
        return SimpleViewHolder(view, listener)
    }

    override fun onBindViewHolder(holder: SimpleViewHolder, position: Int, payloads: MutableList<Any>) {
        if (payloads.isEmpty()) return super.onBindViewHolder(holder, position, payloads)

        val newNumber = payloads[0] as? Int ?: return
        holder.updateNumber(newNumber)
    }

    override fun onBindViewHolder(holder: SimpleViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount() = data.size

    fun submitList(list: List<SimpleModel>) {
        val diffUtilCallback = SimpleDiffCallback(data, list)
        val diffResult = DiffUtil.calculateDiff(diffUtilCallback)

        data = list
        diffResult.dispatchUpdatesTo(this)
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

    interface InteractionsListener {
        fun onClick(model: SimpleModel)
        fun onDeleteClick(model: SimpleModel)
        fun onItemSwiped(position: Int)
        fun onItemMoved(fromPosition: Int, toPosition: Int)
    }
}
