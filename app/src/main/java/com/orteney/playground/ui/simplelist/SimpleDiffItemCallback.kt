package com.orteney.playground.ui.simplelist

import androidx.recyclerview.widget.DiffUtil

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