package com.example.testapplication.modules.expandablelist

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.testapplication.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_parcel.*
import org.jetbrains.anko.sdk19.listeners.onClick
import java.util.*


class ParcelAdapter(private val listener: InteractionsListener? = null) : RecyclerView.Adapter<ParcelAdapter.ViewHolder>() {

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
        private val listener: InteractionsListener?) : RecyclerView.ViewHolder(containerView),
                                                       LayoutContainer {

        private lateinit var currentModel: ParcelModel

        init {
            containerView.onClick { listener?.onParcelClick(currentModel) }
            removeImageView.onClick { listener?.onParcelRemoveClick(currentModel) }
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
