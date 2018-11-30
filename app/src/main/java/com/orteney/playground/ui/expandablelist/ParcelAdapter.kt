package com.orteney.playground.ui.expandablelist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.orteney.playground.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_parcel.*
import java.util.ArrayList

class ParcelAdapter(
    private val listener: InteractionsListener? = null
) : RecyclerView.Adapter<ParcelAdapter.ViewHolder>() {

    private val data = ArrayList<ParcelModel>()

    override fun getItemCount(): Int {
        return data.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_parcel, parent, false)
        return ViewHolder(view, listener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    fun setData(data: List<ParcelModel>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    class ViewHolder(
        override val containerView: View,
        private val listener: InteractionsListener?
    ) : androidx.recyclerview.widget.RecyclerView.ViewHolder(containerView),
        LayoutContainer {

        private lateinit var currentModel: ParcelModel

        init {
            containerView.setOnClickListener { listener?.onParcelClick(currentModel) }
            removeImageView.setOnClickListener { listener?.onParcelRemoveClick(currentModel) }
        }

        fun bind(parcel: ParcelModel) {
            currentModel = parcel
            parcelIdTextView.text = parcel.id
        }
    }

    interface InteractionsListener {
        fun onParcelClick(model: ParcelModel)
        fun onParcelRemoveClick(model: ParcelModel)
    }
}
