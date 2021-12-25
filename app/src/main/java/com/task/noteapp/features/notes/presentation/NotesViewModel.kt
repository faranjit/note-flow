package com.task.noteapp.features.notes.presentation

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.*
import com.task.noteapp.base.BaseViewModel
import com.task.noteapp.component.CreateNoteViewData
import com.task.noteapp.component.toNoteViewData
import com.task.noteapp.features.notes.domain.NotesRepository
import com.task.noteapp.features.notes.domain.model.CreateNoteModel
import com.task.noteapp.features.notes.domain.model.NoteHistoryModel
import com.task.noteapp.features.notes.domain.model.NoteModel
import com.task.noteapp.features.notes.presentation.adapter.history.toHistoryItem
import com.task.noteapp.features.notes.presentation.adapter.note.NoteItem
import com.task.noteapp.features.notes.presentation.adapter.note.toNoteItem
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

/**
 * Created by Bulent Turkmen on 29.09.2021.
 */
@ExperimentalCoroutinesApi
class NotesViewModel(
    private val repository: NotesRepository
) : BaseViewModel() {

    private val notesFlow: Flow<List<NoteModel>> = repository.getNotes()

    private var noteHistoryModel: List<NoteHistoryModel>? = null

    private var notes = MutableLiveData<List<NoteModel>>()
    val notesLiveData: LiveData<List<NoteItem>> = notes.switchMap { n ->
        liveData(viewModelScope.coroutineContext) {
            emit(
                n.map { it.toNoteItem() }
            )
        }
    }

    private val showHistory = MutableLiveData<Long>()
    val showHistoryLiveData: LiveData<Long>
        get() = showHistory

    val createVisibilityObservable = ObservableBoolean(false)
    val historyVisibility = ObservableBoolean(false)
    val noteViewDataObservable = ObservableField<CreateNoteViewData>()


    init {
        viewModelScope.launch {
            notesFlow.flatMapLatest {
                historyVisibility.set(!it.isNullOrEmpty())
                flow {
                    emit(it)
                }
            }.collect {
                notes.value = it
            }
        }
    }

    fun changeCreateNoteVisibility(visible: Boolean) {
        createVisibilityObservable.set(visible)
        historyVisibility.set(!visible)
    }

    fun createNote(noteViewData: CreateNoteViewData) {
        viewModelScope.launch {
            val createNoteModel = CreateNoteModel(
                null,
                noteViewData.title,
                noteViewData.desc,
                noteViewData.imageUrl
            )

            repository.createNote(createNoteModel)
        }
    }

    fun editNote(noteViewData: CreateNoteViewData) {
        viewModelScope.launch {
            val createNoteModel = CreateNoteModel(
                noteViewData.id,
                noteViewData.title,
                noteViewData.desc,
                noteViewData.imageUrl
            )

            repository.editNote(createNoteModel)
        }
    }

    fun deleteNote(noteId: Long) {
        viewModelScope.launch {
            repository.deleteNote(noteId)
        }
    }

    fun openEditNoteView(noteItem: NoteItem) {
        noteViewDataObservable.set(noteItem.toNoteViewData(true))
        changeCreateNoteVisibility(true)
    }

    fun showHistory(noteItem: NoteItem) {
        noteHistoryModel = notes.value?.find {
            it.id == noteItem.id
        }?.history?.also {
            showHistory.value = noteItem.id ?: 0L
        }
    }

    fun getHistoryData(noteId: Long?) = notes.value?.find {
        it.id == noteId
    }?.history?.sortedByDescending {
        it.editDate
    }?.map {
        it.toHistoryItem()
    }
}