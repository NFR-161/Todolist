package com.exampleone.todolist.di

import androidx.lifecycle.ViewModel
import com.exampleone.todolist.presentation.viewmodels.TaskViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(TaskViewModel::class)
    fun bindCoinViewModel(viewModel: TaskViewModel): ViewModel
}
