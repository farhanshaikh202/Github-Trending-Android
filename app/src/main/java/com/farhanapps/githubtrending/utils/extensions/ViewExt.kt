package com.farhanapps.githubtrending.utils.extensions

import android.view.View
import com.google.android.material.snackbar.Snackbar


fun View.snackbar(message: CharSequence, isLengthLong: Boolean = false, callback: () -> Unit = {}) {
    Snackbar.make(
        this,
        message,
        if (isLengthLong) Snackbar.LENGTH_LONG else Snackbar.LENGTH_SHORT
    )
        .setAction("Ok") { callback() }
        .show()
}
