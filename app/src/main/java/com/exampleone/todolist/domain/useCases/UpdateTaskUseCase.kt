package com.exampleone.todolist.domain.useCases

import com.exampleone.todolist.data.TaskModel
import com.exampleone.todolist.domain.TaskRepository

class UpdateTaskUseCase(private val taskRepository: TaskRepository) {

    suspend operator fun invoke(taskModel: TaskModel) {
        return taskRepository.updateTask(taskModel)
    }
}