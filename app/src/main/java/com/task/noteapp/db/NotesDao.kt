package com.task.noteapp.db

import androidx.room.*
import com.task.noteapp.db.entity.NoteEntity
import com.task.noteapp.db.entity.NoteHistoryEntity
import com.task.noteapp.db.entity.NoteWithHistory
import kotlinx.coroutines.flow.Flow

/**
 * Created by Bulent Turkmen on 29.09.2021.
 */
@Dao
interface NotesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: NoteEntity): Long

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun editNote(note: NoteEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHistory(history: NoteHistoryEntity)

    @Delete
    suspend fun removeNote(note: NoteEntity)

    @Query("DELETE FROM note_history WHERE note_id = :noteId")
    suspend fun removeNoteHistory(noteId: Int)

    @Transaction
    @Query("SELECT * FROM notes")
    fun getNotes(): Flow<List<NoteWithHistory>>
}