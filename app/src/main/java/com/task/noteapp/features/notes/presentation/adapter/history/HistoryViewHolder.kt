package com.task.noteapp.features.notes.presentation.adapter.history

import com.task.noteapp.base.BaseViewHolder
import com.task.noteapp.databinding.ItemHistoryListBinding

/**
 * Created by Bulent Turkmen on 30.09.2021.
 */
class HistoryViewHolder(
    binding: ItemHistoryListBinding
) : BaseViewHolder<HistoryItem, ItemHistoryListBinding>(binding) {

    override fun bind(item: HistoryItem) {
        binding.item = item
    }
}