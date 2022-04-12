package com.exampleone.todolist.di

import com.exampleone.todolist.data.repository.TaskRepositoryImpl
import com.exampleone.todolist.domain.TaskRepository
import dagger.Binds
import dagger.Module


@Module
interface DataModule {

    @Binds
    fun bindTaskRepository(impl: TaskRepositoryImpl): TaskRepository
}