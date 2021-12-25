package com.task.noteapp.features.notes.data.datasource

import com.task.noteapp.BaseUnitTest
import com.task.noteapp.db.entity.NoteEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

/**
 * Created by Bulent Turkmen on 29.09.2021.
 */
@ExperimentalCoroutinesApi
class NotesLocalDataSourceTest : BaseUnitTest() {

    private lateinit var localDataSource: NotesLocalDataSource

    private val dao = FakeDao()

    @Before
    override fun setup() {
        super.setup()
        localDataSource = NotesLocalDataSource(dao)
    }

    @Test
    fun `should createNote be successful`() = runBlockingTest {
        // given
        val previousSize = dao.notes.size

        // when
        val note = NoteEntity(
            61, "test title", "test desc"
        )
        localDataSource.createNote(note)

        // then
        assertTrue(dao.notes.size > previousSize)
        assertTrue(note.id == dao.notes[previousSize].note.id)
    }

    @Test
    fun `should editNote be successful`() = runBlockingTest {
        // given
        val insertedNote = dao.notes[0].note
        val note = NoteEntity(
            insertedNote.id,
            "edit test"
        )

        // when
        localDataSource.editNote(note)

        // then
        assertEquals("edit test", dao.notes[0].note.title)
        assert(dao.notes[0].history.isNotEmpty())
    }

    @Test
    fun `should deleteNote be successful`() = runBlockingTest {
        // given
        val previousSize = dao.notes.size
        val insertedNote = dao.notes[0].note
        val note = NoteEntity(insertedNote.id)

        // when
        localDataSource.deleteNote(note)

        // then
        assertEquals(previousSize - 1, dao.notes.size)
    }
}