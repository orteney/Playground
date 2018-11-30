package com.orteney.playground.modules.expandablelist

import androidx.recyclerview.widget.DiffUtil
import com.orteney.playground.ui.expandablelist.ExpandableModel

class ExpandableDiffCallback(
    private val oldList: List<ExpandableModel>,
    private val newList: List<ExpandableModel>) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return newList[newItemPosition].id == oldList[oldItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]

        return newItem.items.count() == oldItem.items.count()
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        return null
    }
}