package com.exampleone.todolist.domain.useCases

import com.exampleone.todolist.domain.TaskModel
import com.exampleone.todolist.domain.TaskRepository

class UpdateTaskUseCase(private val taskRepository: TaskRepository) {

    suspend fun updateTask(taskModel: TaskModel) {
        taskRepository.updateTask(taskModel)
    }
}