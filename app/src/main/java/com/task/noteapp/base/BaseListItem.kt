package com.task.noteapp.base

import androidx.recyclerview.widget.DiffUtil

/**
 * Created by Bulent Turkmen on 30.09.2021.
 */
class BaseRecyclerItemCallback<T : BaseListItem<T>> :
    DiffUtil.ItemCallback<T>() {

    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem.areItemsTheSame(newItem)
    }

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem.areContentsTheSame(newItem)
    }
}

interface BaseListItem<T> {
    fun areItemsTheSame(newItem: T): Boolean

    fun areContentsTheSame(newItem: T): Boolean
}