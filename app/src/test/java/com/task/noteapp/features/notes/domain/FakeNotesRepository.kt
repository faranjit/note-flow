package com.task.noteapp.features.notes.domain

import com.task.noteapp.features.notes.domain.model.CreateNoteModel
import com.task.noteapp.features.notes.domain.model.NoteModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.util.*

/**
 * Created by Bulent Turkmen on 30.09.2021.
 */
class FakeNotesRepository(
    private val scope: CoroutineScope
) : NotesRepository {

    private val notesFLow = MutableStateFlow(
        mutableListOf(
            NoteModel(
                1, "test note 1", "test desc 1", "image 1", null, null
            ),
            NoteModel(
                2, "test note 2", "test desc 2", "image 2", null, null
            ),
            NoteModel(
                3, "test note 3", "test desc 3", "image 3", null, null
            )
        )
    )

    override fun getNotes(): Flow<List<NoteModel>> = notesFLow

    override suspend fun createNote(noteModel: CreateNoteModel) {
        scope.launch {
            val notes = notesFLow.value
            notes.add(
                NoteModel(
                    61,
                    noteModel.title,
                    noteModel.desc,
                    noteModel.imageUrl,
                    createDate = Calendar.getInstance().time
                )
            )
            notesFLow.emit(notes)
        }
    }

    override suspend fun editNote(noteModel: CreateNoteModel) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteNote(noteId: Long) {
        scope.launch {
            val notes = notesFLow.value
            notes.removeIf { it.id == noteId }
            notesFLow.emit(notes)
        }
    }
}