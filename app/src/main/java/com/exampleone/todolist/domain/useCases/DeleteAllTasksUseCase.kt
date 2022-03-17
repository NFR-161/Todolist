package com.exampleone.todolist.domain.useCases

import com.exampleone.todolist.domain.TaskRepository

class DeleteAllTasksUseCase(private val taskRepository: TaskRepository) {

    suspend fun deleteAllTasks() {
        taskRepository.deleteAllTasks()
    }
}