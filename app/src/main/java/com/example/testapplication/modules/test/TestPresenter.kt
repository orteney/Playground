package com.example.testapplication.modules.test

import com.arellomobile.mvp.InjectViewState
import com.example.testapplication.common.viper.presenter.BasePresenter
import javax.inject.Inject

@InjectViewState
class TestPresenter @Inject constructor(router: ITestRouter) : BasePresenter<ITestView, ITestRouter>(router) {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
    }
}
