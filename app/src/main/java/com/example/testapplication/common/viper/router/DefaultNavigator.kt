package com.example.testapplication.common.viper.router

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity

/**
 * Created by e.gorev on 10.03.2017.
 */

abstract class DefaultNavigator(activity: AppCompatActivity, containerId: Int) : BaseNavigator(activity, containerId) {

    override fun createIntent(context: Context, screenName: String, transferData: Any?): Intent? =
            when (screenName) {
                //AuthTransitions.LOGIN -> AuthenticationActivity.createIntent(context, AuthTransitions.LOGIN)
                else -> null
            }
}
