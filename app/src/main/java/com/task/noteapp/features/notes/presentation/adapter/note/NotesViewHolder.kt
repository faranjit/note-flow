package com.task.noteapp.features.notes.presentation.adapter.note

import com.task.noteapp.base.BaseViewHolder
import com.task.noteapp.databinding.ItemNoteListBinding
import com.task.noteapp.features.notes.presentation.adapter.click.NoteItemDeleteClickListener
import com.task.noteapp.features.notes.presentation.adapter.click.NoteItemEditClickListener
import com.task.noteapp.features.notes.presentation.adapter.click.NoteItemHistoryClickListener

/**
 * Created by Bulent Turkmen on 30.09.2021.
 */
class NotesViewHolder(
    binding: ItemNoteListBinding,
    private val deleteClickListener: NoteItemDeleteClickListener,
    private val editClickListener: NoteItemEditClickListener,
    private val historyClickListener: NoteItemHistoryClickListener
) : BaseViewHolder<NoteItem, ItemNoteListBinding>(binding) {

    override fun bind(item: NoteItem) {
        binding.item = item

        binding.imgDel.setOnClickListener {
            deleteClickListener.onDeleteClick(item)
        }

        binding.imgEdit.setOnClickListener {
            editClickListener.onEditClick(item)
        }

        binding.txtEdited.setOnClickListener {
            historyClickListener.onHistoryClick(item)
        }
    }
}