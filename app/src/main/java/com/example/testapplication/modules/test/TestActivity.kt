package com.example.testapplication.modules.test

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.testapplication.R
import com.example.testapplication.TestApplication
import com.example.testapplication.common.Layout
import com.example.testapplication.common.viper.view.BaseActivity
import com.example.testapplication.di.AppComponent
import kotlinx.android.synthetic.main.activity_test.*
import java.util.*
import javax.inject.Inject

@Layout(R.layout.activity_test)
class TestActivity : BaseActivity(), ITestView {

    @Inject
    @InjectPresenter
    lateinit var presenter: TestPresenter

    @ProvidePresenter
    fun providePresenter() = presenter

    private lateinit var adapter: TestAdapter

    override fun createNavigator() = TestNavigator(this, R.id.rootLayout)

    private val models = ArrayList<TestModel>()

    override fun onCreate(savedInstanceState: Bundle?) {

        val appComponent = (application as TestApplication).appComponent
        resolveDependencies(appComponent)

        super.onCreate(savedInstanceState)

        initViews()
    }

    fun resolveDependencies(appComponent: AppComponent) {
        DaggerTestComponent.builder()
            .appComponent(appComponent)
            .testModule(TestModule(this, TestRouter(router)))
            .build()
            .inject(this)
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

    private fun randomModel(): TestModel {
        return TestModel(UUID.randomUUID().toString())
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
        models.sortBy { it.id }
        adapter.setData(models)
    }

    private fun removeItem(model: TestModel) {
        models.remove(model)
        adapter.setData(models)
    }

    private fun initViews() {
        adapter = TestAdapter(this::removeItem)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@TestActivity)
            adapter = this@TestActivity.adapter
        }
    }
}
