package com.exampleone.todolist.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.exampleone.todolist.data.TaskModel
import com.exampleone.todolist.data.TaskRepository
import kotlinx.coroutines.launch

class TaskViewModel(private val taskRepository: TaskRepository) : ViewModel() {

    val tasks = taskRepository.tasks

    fun startInsert(nameTask:String) {
        insert(TaskModel(0, nameTask, false))
    }

    fun insert(taskModel: TaskModel) = viewModelScope.launch{

        taskRepository.insertTask(taskModel)
    }

    fun updateTask(taskModel: TaskModel) = viewModelScope.launch{

        taskRepository.updateTask(taskModel)
    }

    fun delete(taskModel: TaskModel) = viewModelScope.launch{

        taskRepository.deleteTask(taskModel)
    }

    fun deleteAll() = viewModelScope.launch{

        taskRepository.deleteAllTasks()
    }


}