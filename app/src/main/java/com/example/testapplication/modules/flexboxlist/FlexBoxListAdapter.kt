package com.example.testapplication.modules.flexboxlist

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.testapplication.R
import kotlinx.android.synthetic.main.item_test.view.*
import org.jetbrains.anko.sdk19.listeners.onClick
import java.util.*

class FlexBoxListAdapter(val onClick: ((model: FlexBoxModel) -> Unit)? = null) : RecyclerView.Adapter<FlexBoxListAdapter.ViewHolder>() {

    private val data = ArrayList<FlexBoxModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_flexbox, parent, false)
        return ViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun setData(data: List<FlexBoxModel>) {
        val diffUtilCallback = FlexBoxDiffCallback(this.data, data)
        val diffResult = DiffUtil.calculateDiff(diffUtilCallback)

        this.data.clear()
        this.data.addAll(data)

        diffResult.dispatchUpdatesTo(this)
    }

    class ViewHolder(
        itemView: View,
        val onClick: ((model: FlexBoxModel) -> Unit)?) : RecyclerView.ViewHolder(itemView) {

        fun bind(testModel: FlexBoxModel) {
            itemView.apply {
                idTextView.text = testModel.text
                onClick { onClick?.invoke(testModel) }
            }
        }
    }
}
