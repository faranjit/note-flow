package com.task.noteapp.features.notes.data.datasource

import com.task.noteapp.db.NotesDao
import com.task.noteapp.db.entity.NoteEntity
import com.task.noteapp.db.entity.NoteHistoryEntity
import com.task.noteapp.db.entity.NoteWithHistory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

/**
 * Created by Bulent Turkmen on 30.09.2021.
 */
class FakeDao : NotesDao {

    val notes = mutableListOf(
        NoteWithHistory(
            NoteEntity(
                1, "test note 1", "test desc 1", "image 1"
            ),
            mutableListOf()
        ),
        NoteWithHistory(
            NoteEntity(
                2, "test note 2", "test desc 2", "image 2"
            ),
            mutableListOf()
        ),
        NoteWithHistory(
            NoteEntity(
                3, "test note 3", "test desc 3", "image 3"
            ),
            mutableListOf()
        )
    )

    override suspend fun insertNote(note: NoteEntity): Long {
        notes.add(NoteWithHistory(note, mutableListOf()))
        return note.id ?: -1L
    }

    override suspend fun editNote(note: NoteEntity) {
        val index = notes.indexOfFirst { it.note.id == note.id }
        notes[index] = NoteWithHistory(note, emptyList())
        insertHistory(NoteHistoryEntity(1, note.id))
    }

    override suspend fun insertHistory(history: NoteHistoryEntity) {
        val index = notes.indexOfFirst { it.note.id == history.noteId }
        notes[index] = NoteWithHistory(notes[index].note, notes[index].history + history)
    }

    override suspend fun removeNote(note: NoteEntity) {
        notes.removeIf { it.note.id == note.id }
    }

    override suspend fun removeNoteHistory(noteId: Int) {
        TODO("Not yet implemented")
    }

    override fun getNotes(): Flow<List<NoteWithHistory>> = MutableStateFlow(
        notes.map { it }
    )
}