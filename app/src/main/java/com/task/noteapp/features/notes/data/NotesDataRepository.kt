package com.task.noteapp.features.notes.data

import com.task.noteapp.db.entity.NoteEntity
import com.task.noteapp.db.entity.NoteHistoryEntity
import com.task.noteapp.features.notes.data.datasource.NotesLocalDataSource
import com.task.noteapp.features.notes.domain.NotesRepository
import com.task.noteapp.features.notes.domain.mapper.NoteModelMapper
import com.task.noteapp.features.notes.domain.model.CreateNoteModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Created by Bulent Turkmen on 29.09.2021.
 */
class NotesDataRepository @Inject constructor(
    private val localDataSource: NotesLocalDataSource
) : NotesRepository {

    override fun getNotes() =
        localDataSource.getNotes().map { notes ->
            notes.map {
                NoteModelMapper.mapFromEntity(it)
            }.sortedByDescending { it.id }
        }

    override suspend fun createNote(noteModel: CreateNoteModel) {
        localDataSource.createNote(
            NoteEntity(
                null,
                noteModel.title,
                noteModel.desc,
                noteModel.imageUrl
            )
        )
    }

    override suspend fun editNote(noteModel: CreateNoteModel) {
        val noteEntity = NoteEntity(
            noteModel.id,
            noteModel.title,
            noteModel.desc,
            noteModel.imageUrl
        )

        localDataSource.editNote(noteEntity)
        createEditHistory(noteEntity)
    }

    override suspend fun deleteNote(noteId: Long) {
        localDataSource.deleteNote(NoteEntity(noteId))
    }

    private suspend fun createEditHistory(noteEntity: NoteEntity) {
        with(localDataSource) {
            insertHistory(
                NoteHistoryEntity(
                    id = null,
                    noteId = noteEntity.id,
                    title = noteEntity.title,
                    desc = noteEntity.desc,
                    imageUrl = noteEntity.imageUrl
                )
            )
        }
    }
}