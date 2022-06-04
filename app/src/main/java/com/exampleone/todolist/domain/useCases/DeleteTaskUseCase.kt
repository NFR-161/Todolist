package com.exampleone.todolist.domain.useCases

import com.exampleone.todolist.domain.TaskItem
import com.exampleone.todolist.domain.TaskRepository
import javax.inject.Inject

class DeleteTaskUseCase @Inject constructor(private val taskRepository: TaskRepository) {

    suspend operator fun invoke(taskItem: TaskItem) {
        return taskRepository.deleteTask(taskItem)
    }
}