package com.example.testapplication.common.viper.view

import android.support.annotation.StringRes

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

/**
 * Created by denischuvasov on 21.02.17.
 */

interface IBaseView : MvpView {

    fun showMessage(message: String)

    fun showMessage(@StringRes id: Int)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showError(errorMessage: String)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showError(@StringRes id: Int, vararg args: String)
}
