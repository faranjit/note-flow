package com.task.noteapp.features.notes.presentation

import com.task.noteapp.base.BaseViewModelFactory
import com.task.noteapp.features.notes.data.NotesDataRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

/**
 * Created by Bulent Turkmen on 29.09.2021.
 */
@ExperimentalCoroutinesApi
class NotesViewModelFactory @Inject constructor(
    private val repository: NotesDataRepository
) : BaseViewModelFactory<NotesViewModel>() {

    override fun provideViewModel() = NotesViewModel(repository)
}