package com.task.noteapp.features.notes.presentation.adapter.note

import com.task.noteapp.base.BaseListItem
import com.task.noteapp.features.notes.domain.model.NoteModel
import java.util.*

/**
 * Created by Bulent Turkmen on 30.09.2021.
 */
data class NoteItem(
    val id: Long?,
    val title: String? = null,
    val desc: String? = null,
    val imageUrl: String? = null,
    val createDate: Date? = null,
    val isEdited: Boolean? = false
) : BaseListItem<NoteItem> {

    override fun areItemsTheSame(newItem: NoteItem) = this.id == newItem.id

    override fun areContentsTheSame(newItem: NoteItem) = this == newItem
}

fun NoteModel.toNoteItem() = NoteItem(
    id, title, desc, imageUrl, createDate, !history.isNullOrEmpty()
)