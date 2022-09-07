package com.exampleone.todolist.domain.useCases

import com.exampleone.todolist.domain.TaskRepository
import javax.inject.Inject

class DeleteAllTasksUseCase @Inject constructor(private val taskRepository: TaskRepository) {

    suspend operator fun invoke() {
        return taskRepository.deleteAllTasks()
    }


}