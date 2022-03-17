package com.exampleone.todolist.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.exampleone.todolist.domain.*
import kotlinx.coroutines.launch

class TaskViewModel(
    getTaskListUseCase: GetTaskListUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase,
    private val insertTaskUseCase: InsertTaskUseCase,
    private val deleteAllTasksUseCase: DeleteAllTasksUseCase,
    private val updateTaskUseCase: UpdateTaskUseCase
) : ViewModel() {

    val tasks = getTaskListUseCase.tasks

    fun startUpdateTask(idTask: Int, nameTask: String) {
        updateTask(TaskModel(idTask, nameTask, false))
    }

    fun startInsert(nameTask: String) {
        insert(TaskModel(0, nameTask, false))
    }

    fun insert(taskModel: TaskModel) = viewModelScope.launch {
        insertTaskUseCase.insertTask(taskModel)
    }

    fun updateTask(taskModel: TaskModel) = viewModelScope.launch {
        updateTaskUseCase.updateTask(taskModel)
    }

    fun delete(taskModel: TaskModel) = viewModelScope.launch {
        deleteTaskUseCase.deleteTask(taskModel)
    }

    fun deleteAll() = viewModelScope.launch {
        deleteAllTasksUseCase.deleteAllTasks()
    }


}