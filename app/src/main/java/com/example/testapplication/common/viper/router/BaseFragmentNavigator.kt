package com.example.testapplication.common.viper.router

import android.content.Context
import android.content.Intent
import android.support.v4.app.FragmentManager
import ru.terrakok.cicerone.android.SupportFragmentNavigator
import ru.terrakok.cicerone.commands.Command


abstract class BaseFragmentNavigator(private val context: Context, private val fragmentManager: FragmentManager, containerId: Int) : SupportFragmentNavigator(fragmentManager, containerId) {

    override fun applyCommand(command: Command) {
        when (command) {
            is PerformAction -> backToRootWithAction(command)
            else -> super.applyCommand(command)
        }
    }

    private fun backToRootWithAction(command: PerformAction) {
        val action = command.action
        val transferData = command.transferData
        val actionIntent = Intent(action)
        actionIntent.putExtras(transferData)
        context.sendBroadcast(actionIntent)
    }
}
