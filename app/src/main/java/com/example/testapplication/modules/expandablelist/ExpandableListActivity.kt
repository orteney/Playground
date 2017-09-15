package com.example.testapplication.modules.expandablelist

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import com.example.testapplication.R
import com.example.testapplication.modules.simplelist.SimpleModel
import kotlinx.android.synthetic.main.activity_simple_list.*
import java.util.*


class ExpandableListActivity : AppCompatActivity(), ExpandableListAdapter.InteractionsListener {

    private lateinit var adapter: ExpandableListAdapter

    private val models = ArrayList<ExpandableModel>()

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
        models.add(randomExpandableModel())
        updateData()
    }

    private fun randomExpandableModel(): ExpandableModel {
        val random = Random()

        return ExpandableModel(
            id = UUID.randomUUID().toString(),
            title = "Title",
            subTitle = "SubTitle",
            items = (0 until random.nextInt(10)).map { randomParcelModel() }
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

    private fun initViews() {
        adapter = ExpandableListAdapter(this)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@ExpandableListActivity.adapter
        }
    }
}