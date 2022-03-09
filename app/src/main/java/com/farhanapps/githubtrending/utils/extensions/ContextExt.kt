package com.farhanapps.githubtrending.utils.extensions

import android.content.Context
import android.widget.Toast

fun Context.toast(message: CharSequence, isLengthLong: Boolean = false) =
    Toast.makeText(
        this, message, if (isLengthLong) {
            Toast.LENGTH_LONG
        } else {
            Toast.LENGTH_SHORT
        }
    ).show()

