package com.gnz.koinghub.application.extensions

import android.content.Context
import com.gnz.koinghub.R


fun String.language(context: Context): String = if (this == "null") {
    context.getString(R.string.unknown)
} else this