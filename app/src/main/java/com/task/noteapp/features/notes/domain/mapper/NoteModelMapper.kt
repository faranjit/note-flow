package com.task.noteapp.features.notes.domain.mapper

import com.task.noteapp.db.entity.NoteWithHistory
import com.task.noteapp.features.notes.domain.model.NoteHistoryModel
import com.task.noteapp.features.notes.domain.model.NoteModel

/**
 * Created by Bulent Turkmen on 30.09.2021.
 */
object NoteModelMapper {

    fun mapFromEntity(noteEntity: NoteWithHistory): NoteModel {
        val history = noteEntity.history.map {
            NoteHistoryModel(
                title = it.title,
                desc = it.desc,
                imageUrl = it.imageUrl,
                historyId = it.id,
                editDate = it.editDate,
            )
        }

        return noteEntity.note.run {
            NoteModel(id, title, desc, imageUrl, history, createDate)
        }
    }
}