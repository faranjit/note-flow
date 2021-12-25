package com.task.noteapp.features.notes.presentation

import android.content.Context
import android.os.Bundle
import android.view.ActionMode
import android.view.Menu
import android.view.MenuItem
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.viewModels
import com.task.noteapp.R
import com.task.noteapp.base.BaseActivity
import com.task.noteapp.databinding.ActivityNotesBinding
import com.task.noteapp.features.notes.presentation.adapter.click.NoteItemDeleteClickListener
import com.task.noteapp.features.notes.presentation.adapter.click.NoteItemEditClickListener
import com.task.noteapp.features.notes.presentation.adapter.click.NoteItemHistoryClickListener
import com.task.noteapp.features.notes.presentation.adapter.note.NoteItem
import com.task.noteapp.features.notes.presentation.adapter.note.NotesAdapter
import com.task.noteapp.features.notes.presentation.history.HistoryDialogFragment
import com.task.noteapp.viewBinding
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
class NotesActivity : BaseActivity<NotesViewModel, ActivityNotesBinding>(),
    ActionMode.Callback,
    NoteItemDeleteClickListener,
    NoteItemEditClickListener,
    NoteItemHistoryClickListener {

    @Inject
    lateinit var viewModelFactory: NotesViewModelFactory

    private val binding by viewBinding(ActivityNotesBinding::inflate)

    private val notesAdapter = NotesAdapter(this, this, this)

    private val actionModeCallback: ActionMode.Callback = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.run {
            setSupportActionBar(topAppBar)
            recyclerNotes.adapter = notesAdapter

            btnCreateNote.setOnClickListener {
                openCreateNote()
            }
        }

        observe()
    }

    override fun provideViewModel() = viewModels<NotesViewModel> { viewModelFactory }.value

    override fun provideBinding(): ActivityNotesBinding = binding

    override fun bindViewModel() {
        binding.viewModel = viewModel
    }

    override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.top_app_bar, menu)
        return true
    }

    override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?) = false

    override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.create -> {
                createNote()
                mode?.finish()
                true
            }
            else -> false
        }
    }

    override fun onDestroyActionMode(mode: ActionMode?) {
        viewModel.changeCreateNoteVisibility(false)
        binding.layoutCreateNote.reset()
    }

    override fun onDeleteClick(note: NoteItem) {
        if (note.id == null) {
            Toast.makeText(this, getString(R.string.delete_note_error), Toast.LENGTH_SHORT).show()
        } else {
            viewModel.deleteNote(note.id)
        }
    }

    override fun onEditClick(note: NoteItem) {
        openEditNote(note)
    }

    override fun onHistoryClick(note: NoteItem) {
        viewModel.showHistory(note)
    }

    private fun observe() {
        viewModel.notesLiveData.observe(this) {
            notesAdapter.submitList(it)
        }

        viewModel.showHistoryLiveData.observe(this) {
            HistoryDialogFragment.newInstance(it).show(supportFragmentManager, "")
        }
    }

    private fun openCreateNote() {
        binding.topAppBar.startActionMode(actionModeCallback).run {
            title = resources.getString(R.string.create_new_note)
        }
        viewModel.changeCreateNoteVisibility(true)
    }

    private fun openEditNote(note: NoteItem) {
        binding.topAppBar.startActionMode(actionModeCallback).run {
            title = resources.getString(R.string.edit_note)
        }
        viewModel.openEditNoteView(note)
    }

    private fun createNote() {
        (getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager)?.hideSoftInputFromWindow(
            binding.root.windowToken,
            0
        )

        binding.layoutCreateNote.getData()?.let {
            viewModel.changeCreateNoteVisibility(false)
            if (it.isEdit) {
                viewModel.editNote(it)
            } else {
                viewModel.createNote(it)
            }
        }
    }
}