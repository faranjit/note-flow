package com.task.noteapp.features.notes.presentation.adapter.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.task.noteapp.R
import com.task.noteapp.base.BaseAdapter
import com.task.noteapp.databinding.ItemHistoryListBinding

/**
 * Created by Bulent Turkmen on 30.09.2021.
 */
class HistoryAdapter : BaseAdapter<HistoryItem, ItemHistoryListBinding, HistoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val binding = DataBindingUtil.inflate<ItemHistoryListBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_history_list,
            parent,
            false
        )

        return HistoryViewHolder(binding)
    }
}