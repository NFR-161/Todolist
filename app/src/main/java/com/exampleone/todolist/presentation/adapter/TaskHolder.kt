package com.exampleone.todolist.presentation.adapter

import android.text.SpannableString
import android.text.style.StrikethroughSpan
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.exampleone.todolist.databinding.TaskItemBinding
import com.exampleone.todolist.domain.TaskItem
import com.google.android.material.checkbox.MaterialCheckBox

class TaskHolder(
    private val binding: TaskItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        task: TaskItem,
        strikeThrough: (MaterialCheckBox, TaskItem) -> Unit,
        startPencil: () -> Unit,
        editTask: (TaskItem) -> Unit
    ) {
        binding.apply {
            if (task.isDone) {
                val sp = SpannableString(task.name)
                sp.setSpan(StrikethroughSpan(), 0, task.name.length, 0)
                nameTask.text = sp
                nameTask.isChecked = true

            } else {
                nameTask.text = task.name
                nameTask.isChecked = false
            }
            nameTask.setOnClickListener(View.OnClickListener {
                strikeThrough(nameTask, task)
                if (nameTask.isChecked) {
                    startPencil()
                }
            })
            nameTask.setOnLongClickListener(View.OnLongClickListener {
                if (!nameTask.isChecked && !task.isDone) {
                    editTask(task)
                }
                true
            })
        }
    }
}

