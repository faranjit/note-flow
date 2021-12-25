package com.task.noteapp.features.notes.domain

import com.task.noteapp.features.notes.domain.model.CreateNoteModel
import com.task.noteapp.features.notes.domain.model.NoteModel
import kotlinx.coroutines.flow.Flow

/**
 * Created by Bulent Turkmen on 29.09.2021.
 */
interface NotesRepository {

    /**
     * Returns all notes with edit history
     */
    fun getNotes(): Flow<List<NoteModel>>

    /**
     * Creates a new note entity in db
     */
    suspend fun createNote(noteModel: CreateNoteModel)

    /**
     * Updates the note entity in db
     */
    suspend fun editNote(noteModel: CreateNoteModel)

    suspend fun deleteNote(noteId: Long)
}