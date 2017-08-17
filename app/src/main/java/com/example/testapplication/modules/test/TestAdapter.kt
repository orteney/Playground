package com.example.testapplication.modules.test

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.testapplication.R
import kotlinx.android.synthetic.main.item_test.view.*
import java.util.*

class TestAdapter(val onClick: ((model: TestModel) -> Unit)? = null) : RecyclerView.Adapter<TestAdapter.ViewHolder>() {

    private val data = ArrayList<TestModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_test, parent, false)
        return ViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun setData(data: List<TestModel>) {
        val diffUtilCallback = TestDiffCallback(this.data, data)
        val diffResult = DiffUtil.calculateDiff(diffUtilCallback)

        this.data.clear()
        this.data.addAll(data)

        diffResult.dispatchUpdatesTo(this)
    }

    class ViewHolder(
        itemView: View,
        val onClick: ((model: TestModel) -> Unit)?) : RecyclerView.ViewHolder(itemView) {

        fun bind(testModel: TestModel) {
            itemView.apply {
                idTextView.text = testModel.id
                setOnClickListener { onClick?.invoke(testModel) }
            }
        }
    }
}
