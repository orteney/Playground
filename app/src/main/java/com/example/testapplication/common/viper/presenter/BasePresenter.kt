package com.example.testapplication.common.viper.presenter

import android.support.annotation.VisibleForTesting
import com.arellomobile.mvp.MvpPresenter
import com.example.testapplication.common.viper.router.IBaseRouter
import com.example.testapplication.common.viper.view.IBaseView
import io.reactivex.disposables.CompositeDisposable

abstract class BasePresenter<View : IBaseView, out Router : IBaseRouter>(protected val router: Router) : MvpPresenter<View>() {

    open protected val subscriptions = CompositeDisposable()

    open fun onBackPressed() {
        router.back()
    }

    override fun onDestroy() {
        super.onDestroy()
        subscriptions.clear()
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    override public fun onFirstViewAttach() {
        super.onFirstViewAttach()
    }

    open fun onViewPause() {}

    open fun onViewResume() {}
}
