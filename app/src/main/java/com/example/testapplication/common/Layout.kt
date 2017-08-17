package com.example.testapplication.common

import android.support.annotation.LayoutRes

@Retention
@Target(AnnotationTarget.CLASS, AnnotationTarget.FILE)
annotation class Layout(@LayoutRes val value: Int)
