package com.task.noteapp.features.notes.presentation.adapter.history

import com.task.noteapp.base.BaseListItem
import com.task.noteapp.features.notes.domain.model.NoteHistoryModel
import java.util.*

/**
 * Created by Bulent Turkmen on 30.09.2021.
 */
data class HistoryItem(
    val title: String?,
    val desc: String?,
    val imageUrl: String?,
    val editDate: Date?
) : BaseListItem<HistoryItem> {

    override fun areItemsTheSame(newItem: HistoryItem) = this == newItem

    override fun areContentsTheSame(newItem: HistoryItem) = this == newItem
}

fun NoteHistoryModel.toHistoryItem() = HistoryItem(
    title, desc, imageUrl, editDate
)