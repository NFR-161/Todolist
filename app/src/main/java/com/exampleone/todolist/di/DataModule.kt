package com.exampleone.todolist.di

import android.app.Application
import com.exampleone.todolist.data.Database
import com.exampleone.todolist.data.TaskDao
import com.exampleone.todolist.data.repository.TaskRepositoryImpl
import com.exampleone.todolist.domain.TaskRepository
import dagger.Binds
import dagger.Module
import dagger.Provides


@Module
interface DataModule {

    @ApplicationScope
    @Binds
    fun bindTaskRepository(impl: TaskRepositoryImpl): TaskRepository

    companion object {
        @ApplicationScope
        @Provides
        fun provideTaskInfoDao(
            application: Application
        ): TaskDao {
            return Database.getInstance(application).taskDAO
        }
    }
}