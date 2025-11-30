package com.example.core.presentation.extensions

fun Double.format(): String {
    return if (this % 1.0 == 0.0) {
        this.toInt().toString()
    } else {
        String.format("%.1f", this)
    }
}