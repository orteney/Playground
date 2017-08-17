package com.example.testapplication.modules.simplelist

import android.support.v7.widget.RecyclerView

interface ItemTouchHelperAdapter {
    fun onItemMove(fromPosition: Int, toPosition: Int)
    fun onItemDismiss(position: Int)
    fun onStartInteractions(viewHolder: RecyclerView.ViewHolder)
    fun onCompleteInteractions(viewHolder: RecyclerView.ViewHolder)
}