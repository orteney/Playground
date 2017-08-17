package com.example.testapplication.common

fun <T> MutableList<T>.swap(from: Int, to: Int) {
    val tmp = this[from] // 'this' corresponds to the list
    this[from] = this[to]
    this[to] = tmp
}