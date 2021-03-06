package com.orteney.playground.ui.flexboxlist

import android.os.Bundle
import android.view.View
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.orteney.playground.R
import com.orteney.playground.ui.common.BaseFragment
import kotlinx.android.synthetic.main.fragment_simple_list.*
import java.util.ArrayList
import java.util.Random
import java.util.UUID

class FlexBoxListFragment : BaseFragment() {

    override val layoutRes: Int = R.layout.fragment_simple_list

    private lateinit var adapter: FlexBoxListAdapter

    private val models = ArrayList<FlexBoxModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        adapter = FlexBoxListAdapter(onClick = this::removeItem)
        recyclerView.apply {
            layoutManager = FlexboxLayoutManager(context).apply {
                flexDirection = FlexDirection.ROW
                flexWrap = FlexWrap.WRAP
                justifyContent = JustifyContent.CENTER
            }

            adapter = this@FlexBoxListFragment.adapter
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

    private fun removeItem(model: FlexBoxModel) {
        models.firstOrNull { it.id == model.id }?.let {
            models.remove(it)
            adapter.setData(models)
        }
    }

    private fun addItem() {
        models.add(randomModel())
        adapter.setData(models)
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

    private fun clearAll() {
        models.clear()
        adapter.setData(models)
    }

    private fun sort() {
        models.sortBy { it.text }
        adapter.setData(models)
    }
}
