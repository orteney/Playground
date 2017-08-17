package com.example.testapplication.common.viper.router

import android.os.Bundle

import ru.terrakok.cicerone.commands.Command


class PerformAction(val action: String, var transferData: Bundle?) : Command
