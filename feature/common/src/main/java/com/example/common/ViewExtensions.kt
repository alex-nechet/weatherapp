package com.example.common

import android.view.View


fun View.hide(gone: Boolean = true) {
    this.visibility = when {
        gone -> View.GONE
        else -> View.INVISIBLE
    }
}

fun View.show() {
    this.visibility = View.VISIBLE
}