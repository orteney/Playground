package com.orteney.playground.ui.simplelist

import androidx.recyclerview.widget.RecyclerView

interface ItemTouchHelperAdapter {
    fun onItemMove(fromPosition: Int, toPosition: Int)
    fun onItemDismiss(position: Int)
    fun onStartInteractions(viewHolder: RecyclerView.ViewHolder)
    fun onCompleteInteractions(viewHolder: RecyclerView.ViewHolder)
}