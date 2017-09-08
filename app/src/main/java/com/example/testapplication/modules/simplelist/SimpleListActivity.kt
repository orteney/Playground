package com.example.testapplication.modules.simplelist

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.Menu
import android.view.MenuItem
import com.example.testapplication.R
import com.example.testapplication.common.Layout
import com.example.testapplication.common.swap
import kotlinx.android.synthetic.main.activity_simple_list.*
import java.util.*


@Layout(R.layout.activity_test)
class SimpleListActivity : AppCompatActivity(), SimpleListAdapter.InteractionsListener {

    private lateinit var adapter: SimpleListAdapter

    private val models = ArrayList<SimpleModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple_list)

        initViews()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_simple, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
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

            else -> super.onOptionsItemSelected(item)
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
        models.firstOrNull { it.id == model.id }?.let {
            it.number++
            updateData()
        }
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
        adapter.setData(models)
    }

    private fun initViews() {
        adapter = SimpleListAdapter(this)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@SimpleListActivity)
            adapter = this@SimpleListActivity.adapter
        }

        val callback = SimpleItemTouchHelperCallback(adapter)
        val touchHelper = ItemTouchHelper(callback)
        touchHelper.attachToRecyclerView(recyclerView)
    }
}
