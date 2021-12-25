package com.task.noteapp.db

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.task.noteapp.db.entity.NoteEntity
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import java.util.concurrent.Executors

/**
 * Created by Bulent Turkmen on 30.09.2021.
 */
@RunWith(AndroidJUnit4::class)
class NoteDatabaseTest {

    private lateinit var dao: NotesDao

    private lateinit var db: NoteDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room
            .inMemoryDatabaseBuilder(
                context, NoteDatabase::class.java
            )
            .setTransactionExecutor(Executors.newSingleThreadExecutor())
            .build()
        dao = db.notesDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(IOException::class)
    fun shouldInsertNoteCreateNewNote() = runBlocking {
        // given
        val note = NoteEntity(
            null, "test title", "test desc"
        )

        // when
        val id = dao.insertNote(note)

        // then
        assertTrue(id > 0)
    }
}