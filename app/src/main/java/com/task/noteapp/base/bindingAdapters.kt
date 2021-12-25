package com.task.noteapp.base

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.task.noteapp.di.modules.GlideApp
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Bulent Turkmen on 30.09.2021.
 */

private const val IMAGE_RADIUS = 12

@BindingAdapter("visibility")
fun View.setVisibility(visible: Boolean) {
    visibility = if (visible) View.VISIBLE else View.GONE
}

@BindingAdapter("visibility")
fun View.setVisibility(str: String?) {
    visibility = if (str.isNullOrEmpty()) View.GONE else View.VISIBLE
}

@BindingAdapter("date")
fun AppCompatTextView.setDateText(date: Date) {
    val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    text = dateFormat.format(date)
}

@BindingAdapter("url")
fun setImageUrl(view: AppCompatImageView, url: String?) {
    if (url.isNullOrEmpty()) {
        return
    } else {
        GlideApp.with(view.context)
            .load(url)
            .centerCrop()
            .apply(RequestOptions.bitmapTransform(RoundedCorners(IMAGE_RADIUS)))
            .into(view)
    }
}