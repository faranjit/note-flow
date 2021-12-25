package com.task.noteapp.features.notes.presentation.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.task.noteapp.R
import com.task.noteapp.databinding.DialogHistoryBinding
import com.task.noteapp.features.notes.presentation.NotesViewModel
import com.task.noteapp.features.notes.presentation.adapter.history.HistoryAdapter
import dagger.android.support.AndroidSupportInjection
import kotlinx.coroutines.ExperimentalCoroutinesApi

/**
 * Created by Bulent Turkmen on 30.09.2021.
 */
@ExperimentalCoroutinesApi
class HistoryDialogFragment : DialogFragment() {

    companion object {

        private const val KEY_NOTE_ID = "note_id"

        fun newInstance(noteId: Long): HistoryDialogFragment {
            val args = Bundle()
            args.putLong(KEY_NOTE_ID, noteId)

            val fragment = HistoryDialogFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private lateinit var binding: DialogHistoryBinding

    private val viewModel: NotesViewModel by activityViewModels()

    private val historyAdapter = HistoryAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.dialog_history,
            container,
            false
        )
        binding.viewModel = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerNotes.adapter = historyAdapter
        binding.imgClose.setOnClickListener {
            dismiss()
        }

        historyAdapter.submitList(viewModel.getHistoryData(arguments?.getLong(KEY_NOTE_ID)))
    }

    override fun getTheme(): Int {
        return R.style.DialogTheme
    }
}