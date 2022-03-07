package com.exampleone.todolist.presentation

import androidx.recyclerview.widget.DiffUtil
import com.exampleone.todolist.domain.TaskModel

class TaskItemDiffCallBack: DiffUtil.ItemCallback<TaskModel>() {
    override fun areItemsTheSame(oldItem: TaskModel, newItem: TaskModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: TaskModel, newItem: TaskModel): Boolean {
        return oldItem == newItem
    }
}