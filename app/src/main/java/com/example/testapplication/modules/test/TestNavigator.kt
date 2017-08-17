package com.example.testapplication.modules.test

import android.content.Context
import android.content.Intent
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.example.testapplication.common.viper.router.DefaultNavigator

/**
 * Created by a.kojemyakin on 27.03.2017.
 */

class TestNavigator(activity: AppCompatActivity, containerId: Int) : DefaultNavigator(activity, containerId) {

    override fun createIntent(context: Context, screenName: String, transferData: Any?): Intent? =
            when (screenName) {
                else -> super.createIntent(context, screenName, transferData)
            }

    override fun createFragment(screenKey: String, data: Any?): Fragment? =
            when (screenKey) {
                else -> null
            }
}
