package com.task.noteapp.component

import com.task.noteapp.features.notes.presentation.adapter.note.NoteItem

/**
 * Created by Bulent Turkmen on 30.09.2021.
 */
data class CreateNoteViewData(
    val id: Long? = null,
    val title: String? = null,
    val desc: String? = null,
    val imageUrl: String? = null,
    val isEdit: Boolean = false
)

fun NoteItem.toNoteViewData(isEdit: Boolean = false) = CreateNoteViewData(
    id,
    title,
    desc,
    imageUrl,
    isEdit
)