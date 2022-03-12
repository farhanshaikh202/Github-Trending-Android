package com.farhanapps.githubtrending.utils.extensions

import android.widget.TextView
import androidx.core.text.HtmlCompat

fun TextView.highlight(textToHighLight: String, colorHex: String) {
    text = HtmlCompat.fromHtml(
        text.toString().replace(
            textToHighLight,
            "<font color=\"$colorHex\">$textToHighLight</font>"
        ), HtmlCompat.FROM_HTML_MODE_COMPACT
    )
}