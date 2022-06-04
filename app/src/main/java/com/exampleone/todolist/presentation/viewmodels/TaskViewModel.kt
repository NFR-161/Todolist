package com.exampleone.todolist.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.exampleone.todolist.domain.TaskItem
import com.exampleone.todolist.domain.useCases.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class TaskViewModel @Inject constructor(
    getTaskListUseCase: GetTaskListUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase,
    private val insertTaskUseCase: InsertTaskUseCase,
    private val deleteAllTasksUseCase: DeleteAllTasksUseCase,
    private val updateTaskUseCase: UpdateTaskUseCase
) : ViewModel() {

    val tasks = getTaskListUseCase.tasks

    fun startUpdateTask(idTask: Int, nameTask: String) {
        updateTask(TaskItem(idTask, nameTask, false))
    }

    fun startInsert(nameTask: String) {
        insert(TaskItem(0, nameTask, false))
    }

    fun insert(taskItem: TaskItem) = viewModelScope.launch {
        insertTaskUseCase(taskItem)
    }

    fun updateTask(taskItem: TaskItem) = viewModelScope.launch {
        updateTaskUseCase(taskItem)
    }

    fun delete(taskItem: TaskItem) = viewModelScope.launch {
        deleteTaskUseCase(taskItem)
    }

    fun deleteAll() = viewModelScope.launch {
        deleteAllTasksUseCase()
    }


}