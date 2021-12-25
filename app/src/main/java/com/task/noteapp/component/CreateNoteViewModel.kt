package com.task.noteapp.component

import androidx.databinding.ObservableField

/**
 * Created by Bulent Turkmen on 30.09.2021.
 */
class CreateNoteViewModel {

    private var viewData: CreateNoteViewData? = null
    private var isEditing = false

    val titleObservable = ObservableField<String>()
    val descObservable = ObservableField<String>()
    val imageUrlObservable = ObservableField<String>()

    /**
     * Checks inputs if are valid
     */
    fun verify(): Boolean {
        if (titleObservable.get().isNullOrEmpty()) {
            return false
        }

        return true
    }

    fun getViewData(): CreateNoteViewData {
        val data = CreateNoteViewData(
            id = viewData?.id,
            title = titleObservable.get()?.trim() ?: "",
            desc = descObservable.get()?.trim(),
            imageUrl = imageUrlObservable.get()?.trim(),
            isEdit = isEditing
        )

        clear()

        return data
    }


    fun setViewData(viewData: CreateNoteViewData) {
        this.viewData = viewData.apply {
            titleObservable.set(title)
            descObservable.set(desc)
            imageUrlObservable.set(imageUrl)
            isEditing = isEdit
        }
    }

    /**
     * Clears all inputs after process is done
     */
    fun clear() {
        viewData = null
        titleObservable.set("")
        descObservable.set("")
        imageUrlObservable.set("")
    }
}