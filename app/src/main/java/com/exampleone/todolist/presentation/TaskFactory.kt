package com.exampleone.todolist.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.exampleone.todolist.domain.useCases.*

class TaskFactory(
    private val getTaskListUseCase: GetTaskListUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase,
    private val insertTaskUseCase: InsertTaskUseCase,
    private val deleteAllTasksUseCase: DeleteAllTasksUseCase,
    private val updateTaskUseCase: UpdateTaskUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TaskViewModel::class.java)) {
            return TaskViewModel(
                getTaskListUseCase,
                deleteTaskUseCase,
                insertTaskUseCase,
                deleteAllTasksUseCase,
                updateTaskUseCase
            ) as T
        }
        throw IllegalArgumentException("Unknown View Model class")
    }


}
