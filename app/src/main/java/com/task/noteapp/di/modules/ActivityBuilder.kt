package com.task.noteapp.di.modules

import com.task.noteapp.features.notes.NotesModule
import com.task.noteapp.features.notes.presentation.NotesActivity
import com.task.noteapp.features.notes.presentation.history.HistoryDialogFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector
import kotlinx.coroutines.ExperimentalCoroutinesApi

/**
 * Created by Bulent Turkmen on 29.09.2021.
 */
@Module
abstract class ActivityBuilder {

    @ExperimentalCoroutinesApi
    @ContributesAndroidInjector(modules = [NotesModule::class])
    abstract fun bindNotesActivity(): NotesActivity

    @ExperimentalCoroutinesApi
    @ContributesAndroidInjector(modules = [NotesModule::class])
    abstract fun bindHistoryFragment(): HistoryDialogFragment
}