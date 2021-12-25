package com.task.noteapp.db.entity

import androidx.room.Embedded
import androidx.room.Relation

/**
 * Created by Bulent Turkmen on 29.09.2021.
 */
data class NoteWithHistory(
    @Embedded
    val note: NoteEntity,
    @Relation(
        parentColumn = "note_id",
        entityColumn = "note_id"
    )
    val history: List<NoteHistoryEntity>
)