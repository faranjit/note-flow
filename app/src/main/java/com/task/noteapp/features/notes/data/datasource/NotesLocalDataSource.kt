package com.task.noteapp.features.notes.data.datasource

import com.task.noteapp.db.NotesDao
import com.task.noteapp.db.entity.NoteEntity
import com.task.noteapp.db.entity.NoteHistoryEntity
import javax.inject.Inject

/**
 * Created by Bulent Turkmen on 29.09.2021.
 */
class NotesLocalDataSource @Inject constructor(
    private val dao: NotesDao
) {
    /**
     * Returns all notes with edit history
     */
    fun getNotes() = dao.getNotes()

    /**
     * Creates a new note entity in db
     */
    suspend fun createNote(noteEntity: NoteEntity) = dao.insertNote(noteEntity)

    /**
     * Updates the note entity in db
     */
    suspend fun editNote(noteEntity: NoteEntity) = dao.editNote(noteEntity)

    /**
     * Creates a edit history entity for a note
     */
    suspend fun insertHistory(history: NoteHistoryEntity) = dao.insertHistory(history)

    /**
     * Deletes given note from db
     */
    suspend fun deleteNote(noteEntity: NoteEntity) = dao.removeNote(noteEntity)
}