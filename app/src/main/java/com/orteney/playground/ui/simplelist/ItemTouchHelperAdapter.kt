package com.orteney.playground.ui.simplelist

interface ItemTouchHelperAdapter {
    fun onItemMove(fromPosition: Int, toPosition: Int)
    fun onItemDismiss(position: Int)
    fun onStartInteractions(viewHolder: androidx.recyclerview.widget.RecyclerView.ViewHolder)
    fun onCompleteInteractions(viewHolder: androidx.recyclerview.widget.RecyclerView.ViewHolder)
}