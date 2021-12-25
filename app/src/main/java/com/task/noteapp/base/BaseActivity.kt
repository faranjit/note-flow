package com.task.noteapp.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import dagger.android.AndroidInjection

/**
 * Created by Bulent Turkmen on 29.09.2021.
 */
abstract class BaseActivity<VM : ViewModel, VB : ViewDataBinding> : AppCompatActivity() {

    protected lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        viewModel = provideViewModel()
        bindViewModel()
    }

    abstract fun provideViewModel(): VM

    abstract fun provideBinding(): VB

    abstract fun bindViewModel()
}