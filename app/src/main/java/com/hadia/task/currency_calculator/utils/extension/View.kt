package com.hadia.task.currency_calculator.utils.extension

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadFromUrl(url: String) =
    Glide.with(this).load(url).into(this)
