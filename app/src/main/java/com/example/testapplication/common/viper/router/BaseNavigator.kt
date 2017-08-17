package com.example.testapplication.common.viper.router

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import ru.terrakok.cicerone.commands.Command
import ru.terrakok.cicerone.commands.Forward
import ru.terrakok.cicerone.commands.Replace


abstract class BaseNavigator(private val activity: AppCompatActivity, containerId: Int) : BaseFragmentNavigator(activity, activity.supportFragmentManager, containerId) {

    override fun applyCommand(command: Command) {
        val intent = createIntent(command)

        when {
            intent == null -> super.applyCommand(command)
            command is Forward -> activity.startActivity(intent)
            command is Replace -> {
                activity.startActivity(intent)
                activity.finish()
            }
            command is PerformAction -> {
                val action = command.action
                val transferData = command.transferData
                val actionIntent = Intent(action)
                actionIntent.putExtras(transferData)
                activity.sendBroadcast(actionIntent)
            }
            else -> Log.e(TAG, "Command " + command.javaClass.simpleName + " was not executed!")
        }
    }

    private fun createIntent(command: Command): Intent? =
        when (command) {
            is Replace -> createIntent(activity, command.screenKey, command.transitionData)
            is Forward -> createIntent(activity, command.screenKey, command.transitionData)
            else -> null
        }

    override fun showSystemMessage(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }

    override fun exit() {
        activity.finish()
    }

    protected abstract fun createIntent(context: Context, screenName: String, transferData: Any?): Intent?

    companion object {
        private const val TAG = "BaseNavigator"
    }
}
