package com.example.testapplication.common.viper.view

import android.os.Bundle
import android.support.annotation.StringRes
import android.support.design.widget.Snackbar
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.example.testapplication.common.Layout

abstract class BaseFragment : MvpAppCompatFragment(), IBaseView {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.d(name(), "onCreateView() called with: inflater = [$inflater], container = [$container], savedInstanceState = [$savedInstanceState]")

        val cls = javaClass
        if (!cls.isAnnotationPresent(Layout::class.java))
            return null

        val annotation = cls.getAnnotation(Layout::class.java)
        val view = inflater?.inflate(annotation.value, null)

        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(name(), "onCreate() called with: savedInstanceState = [$savedInstanceState]")
        super.onCreate(savedInstanceState)
    }

    override fun onDestroyView() {
        Log.d(name(), "onDestroyView() called")
        super.onDestroyView()
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(name(), "onDestroy() called")
    }

    protected fun name(): String {
        return javaClass.simpleName
    }

    override fun showMessage(message: String) {
        view?.let { Snackbar.make(it, message, Snackbar.LENGTH_LONG).show() }
    }

    override fun showMessage(id: Int) {
        view?.let { Snackbar.make(it, getString(id), Snackbar.LENGTH_LONG).show() }
    }

    override fun showError(errorMessage: String) {
        //TODO
    }

    override fun showError(@StringRes id: Int, vararg args: String) {
        //TODO
    }
}
