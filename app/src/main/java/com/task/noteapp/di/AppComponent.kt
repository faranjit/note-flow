package com.task.noteapp.di

import com.task.noteapp.NotesApplication
import com.task.noteapp.di.modules.ActivityBuilder
import com.task.noteapp.di.modules.DBModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule

/**
 * Created by Bulent Turkmen on 29.09.2021.
 */
@AppScope
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        DBModule::class,
        ActivityBuilder::class
    ]
)
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: NotesApplication): Builder

        fun build(): AppComponent
    }

    fun inject(app: NotesApplication)
}