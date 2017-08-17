package com.example.testapplication.common.viper.router

import android.os.Bundle

import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.commands.BackTo


class BaseAppRouter : Router() {

    fun backToWithActionResult(screenKey: String?, action: String, data: Bundle) {
        executeCommand(PerformAction(action, data))
        executeCommand(BackTo(screenKey))
    }
}
