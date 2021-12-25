package com.task.noteapp.di.modules

import androidx.room.Room
import com.task.noteapp.NotesApplication
import com.task.noteapp.db.NoteDatabase
import com.task.noteapp.di.AppScope
import dagger.Module
import dagger.Provides

/**
 * Created by Bulent Turkmen on 29.09.2021.
 */
@Module
class DBModule {

    companion object {
        private const val DB_NAME = "notes_db"
    }

    @AppScope
    @Provides
    fun provideDatabase(application: NotesApplication) = Room.databaseBuilder(
        application,
        NoteDatabase::class.java,
        DB_NAME
    ).build()

    @AppScope
    @Provides
    fun provideNotesDao(db: NoteDatabase) = db.notesDao()
}