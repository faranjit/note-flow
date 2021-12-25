package com.task.noteapp.features.notes.presentation.adapter.note

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.task.noteapp.R
import com.task.noteapp.base.BaseAdapter
import com.task.noteapp.databinding.ItemNoteListBinding
import com.task.noteapp.features.notes.presentation.adapter.click.NoteItemDeleteClickListener
import com.task.noteapp.features.notes.presentation.adapter.click.NoteItemEditClickListener
import com.task.noteapp.features.notes.presentation.adapter.click.NoteItemHistoryClickListener

/**
 * Created by Bulent Turkmen on 30.09.2021.
 */
class NotesAdapter(
    private val deleteClickListener: NoteItemDeleteClickListener,
    private val editClickListener: NoteItemEditClickListener,
    private val historyClickListener: NoteItemHistoryClickListener
) : BaseAdapter<NoteItem, ItemNoteListBinding, NotesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val binding = DataBindingUtil.inflate<ItemNoteListBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_note_list,
            parent,
            false
        )

        return NotesViewHolder(
            binding,
            deleteClickListener,
            editClickListener,
            historyClickListener
        )
    }
}