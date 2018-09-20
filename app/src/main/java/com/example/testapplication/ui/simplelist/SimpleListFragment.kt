package com.example.testapplication.ui.simplelist

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.View
import com.example.testapplication.R
import com.example.testapplication.extensions.swap
import com.example.testapplication.ui.common.BaseFragment
import kotlinx.android.synthetic.main.fragment_simple_list.*
import org.jetbrains.anko.appcompat.v7.listeners.onMenuItemClick
import java.util.ArrayList
import java.util.Random
import java.util.UUID

class SimpleListFragment : BaseFragment(), SimpleListAdapter.InteractionsListener {

    override val layoutRes: Int = R.layout.fragment_simple_list

    private val models = ArrayList<SimpleModel>()

    private val adapter: SimpleListAdapter by lazy {
        SimpleListAdapter(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@SimpleListFragment.adapter
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    appBarLayout.isSelected = recyclerView.canScrollVertically(-1)
                }
            })
        }

        val callback = SimpleItemTouchHelperCallback(adapter)
        val touchHelper = ItemTouchHelper(callback)
        touchHelper.attachToRecyclerView(recyclerView)

        toolbar.apply {
            inflateMenu(R.menu.menu_simple)
            onMenuItemClick { item ->
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
        models.add(randomModel())
        updateData()
    }

    private fun randomModel(): SimpleModel {
        return SimpleModel(UUID.randomUUID().toString(), Random().nextInt(25))
    }

    private fun clearAll() {
        models.clear()
        updateData()
    }

    private fun sort() {
        models.sortBy { it.number }
        updateData()
    }

    override fun onClick(model: SimpleModel) {
        val index = models.indexOfFirst { it.id == model.id }
        if (index < 0) return

        val model = models[index]
        models[index] = model.copy(number = model.number + 1)

        updateData()
    }

    override fun onDeleteClick(model: SimpleModel) {
        models.firstOrNull { it.id == model.id }?.let {
            models.remove(it)
            updateData()
        }
    }

    override fun onItemSwiped(position: Int) {
        models.removeAt(position)
        updateData()
    }

    override fun onItemMoved(fromPosition: Int, toPosition: Int) {
        if (fromPosition < toPosition) {
            for (i in fromPosition until toPosition) {
                models.swap(i, i + 1)
            }
        } else {
            for (i in fromPosition downTo toPosition + 1) {
                models.swap(i, i - 1)
            }
        }

        updateData()
    }

    private fun updateData() {
        adapter.submitList(models.toList())
    }
}
