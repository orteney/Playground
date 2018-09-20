package com.example.testapplication.ui.simplelist

import android.support.v7.util.DiffUtil

class SimpleDiffCallback(
    private val oldList: List<SimpleModel>,
    private val newList: List<SimpleModel>
) : DiffUtil.Callback() {

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

        return newItem.number == oldItem.number
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]

        return newItem.number
    }
}

class SimpleDiffItemCallback : DiffUtil.ItemCallback<SimpleModel>() {
    override fun areItemsTheSame(oldItem: SimpleModel, newItem: SimpleModel): Boolean {
        return newItem.id == oldItem.id
    }

    override fun areContentsTheSame(oldItem: SimpleModel, newItem: SimpleModel): Boolean {
        return newItem.number == oldItem.number
    }

    override fun getChangePayload(oldItem: SimpleModel, newItem: SimpleModel): Any {
        return newItem.number
    }
}