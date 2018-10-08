package com.gnz.koinghub.application.extensions

import android.view.View


fun View.visibleOrGone(visible: Boolean) = when (visible) {
    true -> this.visibility = View.VISIBLE
    else -> this.visibility = View.GONE
}