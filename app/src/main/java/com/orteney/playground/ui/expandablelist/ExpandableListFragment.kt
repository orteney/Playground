package com.orteney.playground.ui.expandablelist

import android.os.Bundle
import android.view.View
import com.orteney.playground.R
import com.orteney.playground.ui.common.BaseFragment
import com.orteney.playground.ui.simplelist.SimpleModel
import kotlinx.android.synthetic.main.fragment_simple_list.*
import java.util.ArrayList
import java.util.Random
import java.util.UUID

class ExpandableListFragment : BaseFragment(),
                               ExpandableListAdapter.InteractionsListener {

    override val layoutRes: Int = R.layout.fragment_simple_list

    private lateinit var adapter: ExpandableListAdapter

    private val models = ArrayList<ExpandableModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        adapter = ExpandableListAdapter(this)
        recyclerView.apply {
            layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)
            adapter = this@ExpandableListFragment.adapter
        }

        toolbar.apply {
            inflateMenu(R.menu.menu_simple)
            setOnMenuItemClickListener { item ->
                when (item?.itemId) {
                    R.id.menu_add -> {
                        addItem()
                        true
                    }

                    R.id.menu_clear -> {
                        clearAll()
                        true
                    }

                    R.id.menu_sort -> {
                        sort()
                        true
                    }

                    else -> false
                }
            }
        }
    }

    private fun addItem() {
        models.add(randomExpandableModel())
        updateData()
    }

    private fun randomExpandableModel(): ExpandableModel {
        val random = Random()

        return ExpandableModel(
            id = UUID.randomUUID().toString(),
            title = "Title",
            subTitle = "SubTitle",
            items = (0 until random.nextInt(3)).map { randomParcelModel() }
        )
    }

    private fun randomParcelModel(): ParcelModel {
        val random = Random()
        return ParcelModel("200000000${random.nextInt(10)}${random.nextInt(10)}${random.nextInt(10)}${random.nextInt(10)}")
    }

    private fun clearAll() {
        models.clear()
        updateData()
    }

    private fun sort() {
        models.sortBy { it.items.count() }
        updateData()
    }

    override fun onClick(model: SimpleModel) {
    }

    override fun onDeleteClick(model: SimpleModel) {
        models.firstOrNull { it.id == model.id }?.let {
            models.remove(it)
            updateData()
        }
    }

    override fun onExpandStateChange(position: Int) {
        recyclerView.smoothScrollToPosition(position)
    }

    private fun updateData() {
        adapter.setData(models)
    }
}