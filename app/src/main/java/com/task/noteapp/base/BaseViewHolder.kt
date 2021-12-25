package com.task.noteapp.base

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Bulent Turkmen on 30.09.2021.
 */
abstract class BaseViewHolder<Item : BaseListItem<Item>, VB : ViewDataBinding>(
    val binding: VB
) : RecyclerView.ViewHolder(binding.root) {

    abstract fun bind(item: Item)
}