package com.orteney.playground.ui.flexboxlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.orteney.playground.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_flexbox.*
import java.util.ArrayList

class FlexBoxListAdapter(val onClick: ((model: FlexBoxModel) -> Unit)? = null) :
    RecyclerView.Adapter<FlexBoxListAdapter.ViewHolder>() {

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
        override val containerView: View,
        private val onClick: ((model: FlexBoxModel) -> Unit)?
    ) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {

        private var model: FlexBoxModel? = null

        init {
            cardView.setOnClickListener { model?.let { onClick?.invoke(it) } }
        }

        fun bind(testModel: FlexBoxModel) {
            model = testModel
            idTextView.text = testModel.text
        }
    }
}
