package com.task.noteapp.features.notes.domain.model

import java.util.*

/**
 * Created by Bulent Turkmen on 30.09.2021.
 */
data class NoteModel(
    val id: Long?,
    val title: String? = null,
    val desc: String? = null,
    val imageUrl: String? = null,
    val history: List<NoteHistoryModel>? = null,
    val createDate: Date?
)