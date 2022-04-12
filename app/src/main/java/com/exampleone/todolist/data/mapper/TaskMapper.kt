package com.exampleone.todolist.data.mapper

import com.exampleone.todolist.data.TaskModel
import com.exampleone.todolist.domain.TaskModelInfo

class TaskMapper {

    fun mapInfoToTaskModel(taskModelInfo: TaskModelInfo) = TaskModel(
        id = taskModelInfo.id,
        name = taskModelInfo.name,
        isDone = taskModelInfo.isDone
    )

    fun mapTaskModelToInfo(taskModel: TaskModel) = TaskModelInfo(
        id = taskModel.id,
        name = taskModel.name,
        isDone = taskModel.isDone
    )
}