package com.example.testapplication.common.viper.view

import android.os.Bundle
import android.support.design.widget.Snackbar
import com.arellomobile.mvp.MvpAppCompatActivity
import com.example.testapplication.common.Layout
import com.example.testapplication.common.viper.router.BaseAppRouter
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import javax.inject.Inject

abstract class BaseActivity : MvpAppCompatActivity(), IBaseView {

    @Inject lateinit var navigatorHolder: NavigatorHolder
    @Inject lateinit var router: BaseAppRouter

    protected var navigator: Navigator = createNavigator()

    protected abstract fun createNavigator(): Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val cls = javaClass
        if (!cls.isAnnotationPresent(Layout::class.java))
            throw IllegalArgumentException()

        val annotation = cls.getAnnotation(Layout::class.java)
        setContentView(annotation.value)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }

    override fun onBackPressed() {
//        //TODO: почему не использовать здесь Router?
//        val fragment = supportFragmentManager.findFragmentById(R.id.content)
//
//        if (fragment == null ||
//            fragment !is IBackButtonListener
//            || !fragment.onBackPressed()) {
//            super.onBackPressed()
//        }

        super.onBackPressed()
    }

    override fun showMessage(message: String) {
        Snackbar.make(window.decorView.rootView, message, Snackbar.LENGTH_LONG).show()
    }

    override fun showMessage(id: Int) {
        Snackbar.make(window.decorView.rootView, getString(id), Snackbar.LENGTH_LONG).show()
    }

    override fun showError(errorMessage: String) {
        //TODO implement
    }

    override fun showError(id: Int, vararg args: String) {
        //TODO implement
    }
}
