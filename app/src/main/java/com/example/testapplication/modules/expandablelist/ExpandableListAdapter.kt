package com.example.testapplication.modules.expandablelist

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.testapplication.R
import com.example.testapplication.modules.simplelist.SimpleModel
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_expandable.*
import java.util.*

class ExpandableListAdapter(private val listener: InteractionsListener? = null) : RecyclerView.Adapter<ExpandableListAdapter.ViewHolder>() {

    private val data = ArrayList<ExpandableModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_expandable, parent, false)
        return ViewHolder(view, listener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount() = data.size

    fun setData(data: List<ExpandableModel>) {
        val diffUtilCallback = ExpandableDiffCallback(this.data, data)
        val diffResult = DiffUtil.calculateDiff(diffUtilCallback)
        diffResult.dispatchUpdatesTo(this)

        this.data.clear()
        this.data.addAll(data)
    }

    class ViewHolder(
        override val containerView: View,
        private val listener: InteractionsListener?) : RecyclerView.ViewHolder(containerView),
                                                       LayoutContainer,
                                                       ExpandableView.OnStateChangeListener,
                                                       ExpandableView.ParcelInteractionsListener {

        private lateinit var currentModel: ExpandableModel

        init {
            expandableView.stateChangeListener = this
            expandableView.parcelInteractionsListener = this
        }

        fun bind(model: ExpandableModel) {
            currentModel = model
            expandableView.apply {
                setTitle(model.title)
                setSubtitle(model.subTitle)
                setParcels(model.items)

                if (model.isExpanded) {
                    expand(false)
                } else {
                    collapse(false)
                }
            }
        }

        override fun onExpandStateChanged(isExpanded: Boolean) {
            listener?.onExpandStateChange(adapterPosition)
            currentModel.isExpanded = isExpanded
        }

        override fun onParcelClick(model: ParcelModel) {

        }

        override fun onParcelRemoveClick(model: ParcelModel) {

        }
    }

    interface InteractionsListener {
        fun onClick(model: SimpleModel)
        fun onDeleteClick(model: SimpleModel)
        fun onExpandStateChange(position: Int)
    }
}