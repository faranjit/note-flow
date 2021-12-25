package com.task.noteapp.features.notes.domain.model

/**
 * Created by Bulent Turkmen on 30.09.2021.
 */
data class CreateNoteModel(
    val id: Long? = null,
    val title: String? = null,
    val desc: String? = null,
    val imageUrl: String? = null
)