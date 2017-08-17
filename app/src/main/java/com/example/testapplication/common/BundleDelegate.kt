package com.example.testapplication.common

import android.app.Activity
import android.app.Fragment
import android.os.Bundle
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty


inline fun <reified T> Activity.extras(key: String? = null, defaultValue: T? = null): ReadOnlyProperty<Activity, T> {
    return BundleDelegate { thisRef, property ->
        val bundleKey = key ?: property.name
        getFromBundle(thisRef.intent.extras, bundleKey, defaultValue)
    }
}

inline fun <reified T> Fragment.args(key: String? = null, defaultValue: T? = null): ReadOnlyProperty<Fragment, T> {
    return BundleDelegate { thisRef, property ->
        val bundleKey = key ?: property.name
        getFromBundle(thisRef.arguments, bundleKey, defaultValue)
    }
}

inline fun <reified T> bundle(bundle: Bundle?, key: String? = null, defaultValue: T? = null): ReadOnlyProperty<Nothing?, T> {
    return BundleDelegate { _, property ->
        val bundleKey = key ?: property.name
        getFromBundle(bundle, bundleKey, defaultValue)
    }
}

inline fun <reified T> getFromBundle(bundle: Bundle?,
                                     key: String,
                                     defaultValue: T? = null): T {

    val result = bundle?.get(key) ?: defaultValue

    if (result != null && result !is T) {
        throw ClassCastException("Property for $key has different class type")
    }

    return result as T
}

class BundleDelegate<R, T>(private val getValueBlock: (R, KProperty<*>) -> T) : ReadOnlyProperty<R, T> {

    override fun getValue(thisRef: R, property: KProperty<*>): T {
        return getValueBlock(thisRef, property)
    }
}