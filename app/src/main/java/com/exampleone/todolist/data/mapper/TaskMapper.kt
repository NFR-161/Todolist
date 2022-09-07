package com.exampleone.todolist.data.mapper

import com.exampleone.todolist.data.TaskModel
import com.exampleone.todolist.domain.TaskItem

class TaskMapper {
    fun mapTaskItemToDbModel(taskItem: TaskItem) = TaskModel(
        id = taskItem.id,
        name = taskItem.name,
        isDone = taskItem.isDone
    )

    fun mapDbModelToTaskItem(taskModel: TaskModel) = TaskItem(
        id = taskModel.id,
        name = taskModel.name,
        isDone = taskModel.isDone
    )

}