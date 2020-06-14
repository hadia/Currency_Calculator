package com.hadia.task.CurrencyCalculator.utils.extension

import android.net.Uri
import android.widget.ImageView
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou


fun ImageView.loadFromUrl(url: String) =
    GlideToVectorYou.init()
        .with(this.context.applicationContext)
        .load(Uri.parse(url), this)
