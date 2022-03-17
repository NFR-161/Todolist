package com.exampleone.todolist.domain.useCases

import com.exampleone.todolist.domain.TaskModel
import com.exampleone.todolist.domain.TaskRepository

class DeleteTaskUseCase(private val taskRepository: TaskRepository) {

    suspend fun deleteTask(taskModel: TaskModel) {
        taskRepository.deleteTask(taskModel)
    }
}