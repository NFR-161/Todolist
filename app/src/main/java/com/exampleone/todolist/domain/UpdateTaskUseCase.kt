package com.exampleone.todolist.domain

class UpdateTaskUseCase(private val taskRepository: TaskRepository) {

    suspend fun updateTask(taskModel: TaskModel) {
        taskRepository.updateTask(taskModel)
    }
}