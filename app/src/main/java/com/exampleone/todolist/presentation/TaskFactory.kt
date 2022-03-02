package com.exampleone.todolist.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.exampleone.todolist.repositories.TaskRepository

class TaskFactory (private val taskRepository: TaskRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(TaskViewModel::class.java)){
            return TaskViewModel(taskRepository) as T
        }
        throw IllegalArgumentException("Unknown View Model class")
    }


}
