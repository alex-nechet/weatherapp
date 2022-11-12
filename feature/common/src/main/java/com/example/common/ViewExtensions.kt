package com.example.common

import android.view.View
import android.widget.ImageView
import androidx.annotation.DrawableRes
import coil.load


fun View.hide(gone: Boolean = true) {
    this.visibility = when {
        gone -> View.GONE
        else -> View.INVISIBLE
    }
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun ImageView.loadImage(@DrawableRes drawable: Int?) = this.load(drawable) { crossfade(true) }
