package com.task.noteapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.task.noteapp.db.converters.DateConverter
import com.task.noteapp.db.entity.NoteEntity
import com.task.noteapp.db.entity.NoteHistoryEntity

/**
 * Created by Bulent Turkmen on 29.09.2021.
 */
@Database(
    entities = [
        NoteEntity::class,
        NoteHistoryEntity::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(DateConverter::class)
abstract class NoteDatabase : RoomDatabase() {

    abstract fun notesDao(): NotesDao
}