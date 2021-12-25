package com.task.noteapp.component

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import com.task.noteapp.R
import com.task.noteapp.databinding.LayoutCreateNoteBinding

/**
 * Created by Bulent Turkmen on 29.09.2021.
 */
@BindingAdapter("viewData")
fun CreateNoteView.setData(data: CreateNoteViewData?) {
    data?.let {
        setViewData(it)
    }
}

class CreateNoteView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding = DataBindingUtil.inflate<LayoutCreateNoteBinding>(
        LayoutInflater.from(context),
        R.layout.layout_create_note,
        this,
        true
    )
    private val viewModel = CreateNoteViewModel()

    init {
        if (isInEditMode) {
            View.inflate(context, R.layout.layout_create_note, this)
        } else {
            binding.viewModel = viewModel
        }
    }

    fun setViewData(data: CreateNoteViewData) {
        viewModel.setViewData(data)
    }

    /**
     * Returns information about the note which about to be created
     */
    fun getData(): CreateNoteViewData? {
        viewModel.run {
            return if (verify()) {
                getViewData()
            } else {
                binding.inputTitle.error = context.getString(R.string.error_title)
                null
            }
        }
    }

    fun reset() = viewModel.clear()
}