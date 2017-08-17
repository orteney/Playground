package com.example.testapplication.modules.flexboxlist

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.example.testapplication.R
import com.example.testapplication.common.Layout
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import kotlinx.android.synthetic.main.activity_test.*
import java.util.*

@Layout(R.layout.activity_test)
class FlexBoxListActivity : AppCompatActivity() {

    private lateinit var adapter: FlexBoxListAdapter

    private val models = ArrayList<FlexBoxModel>()

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

    private fun randomModel(): FlexBoxModel {
        return FlexBoxModel(
            id = UUID.randomUUID().toString(),
            text = randomString()
        )
    }

    private fun randomString(): String {
        val random = Random()

        val length = random.nextInt(25)

        return buildString {
            for (i in 0..length) {
                append(i)
            }
        }
    }

    private fun addItem() {
        models.add(randomModel())
        adapter.setData(models)
    }

    private fun clearAll() {
        models.clear()
        adapter.setData(models)
    }

    private fun sort() {
        models.sortBy { it.text }
        adapter.setData(models)
    }

    private fun removeItem(model: FlexBoxModel) {
        models.firstOrNull { it.id == model.id }?.let {
            models.remove(it)
            adapter.setData(models)
        }
    }

    private fun initViews() {
        adapter = FlexBoxListAdapter(onClick = this::removeItem)
        recyclerView.apply {
            layoutManager = FlexboxLayoutManager(this@FlexBoxListActivity).apply {
                flexDirection = FlexDirection.ROW
                flexWrap = FlexWrap.WRAP
                justifyContent = JustifyContent.CENTER
            }

            adapter = this@FlexBoxListActivity.adapter
        }
    }
}
