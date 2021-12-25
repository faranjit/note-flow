package com.task.noteapp.features.notes

import com.task.noteapp.db.NotesDao
import com.task.noteapp.features.notes.data.NotesDataRepository
import com.task.noteapp.features.notes.data.datasource.NotesLocalDataSource
import com.task.noteapp.features.notes.domain.NotesRepository
import dagger.Module
import dagger.Provides

/**
 * Created by Bulent Turkmen on 29.09.2021.
 */
@Module
class NotesModule {

    @Provides
    fun provideNotesLocalDataSource(dao: NotesDao) = NotesLocalDataSource(dao)

    @Provides
    fun provideNotesRepository(localDataSource: NotesLocalDataSource): NotesRepository =
        NotesDataRepository(localDataSource)
}