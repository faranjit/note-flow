package com.task.noteapp.features.notes.presentation

import com.task.noteapp.BaseUnitTest
import com.task.noteapp.component.CreateNoteViewData
import com.task.noteapp.features.notes.domain.FakeNotesRepository
import com.task.noteapp.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

/**
 * Created by Bulent Turkmen on 30.09.2021.
 */
@ExperimentalCoroutinesApi
class NotesViewModelTest : BaseUnitTest() {

    private val repository = FakeNotesRepository(testScope)

    private lateinit var viewModel: NotesViewModel

    @Before
    override fun setup() {
        viewModel = NotesViewModel(repository)
    }

    @Test
    fun `should createNote triggers livedata`() {
        testScope.launch {
            // given
            val createNoteViewData = CreateNoteViewData(
                title = "test title", desc = "test desc"
            )

            val previousSize = viewModel.notesLiveData.getOrAwaitValue().size

            // when
            viewModel.createNote(createNoteViewData)
            cancel()

            // then
            assertTrue(viewModel.notesLiveData.getOrAwaitValue().size > previousSize)
        }
    }

    @Test
    fun `should deleteNote deletes note`() {
        testScope.launch {
            // given
            val notes = viewModel.notesLiveData.getOrAwaitValue()

            // when
            notes.forEach {
                viewModel.deleteNote(it.id ?: 0L)
            }

            //then
            assertEquals(0, viewModel.notesLiveData.getOrAwaitValue().size)
            assertTrue(viewModel.historyVisibility.get())
        }
    }
}