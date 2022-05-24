package com.exampleone.todolist.presentation.adapter


import android.text.SpannableString
import android.text.style.StrikethroughSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.exampleone.todolist.R
import com.exampleone.todolist.databinding.TaskItemBinding
import com.exampleone.todolist.data.TaskModel
import com.google.android.material.checkbox.MaterialCheckBox
import java.util.*
import javax.inject.Inject

class TaskAdapter(
    private val strikeThrough: (MaterialCheckBox, TaskModel) -> Unit,
    private val startPencil: () -> Unit,
    private val editTask: (TaskModel) -> Unit
) : ListAdapter<TaskModel, TaskHolder>(TaskInfoDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: TaskItemBinding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.task_item,
            parent,
            false
        )
        return TaskHolder(binding)

    }

    override fun onBindViewHolder(holder: TaskHolder, position: Int) {
        holder.bind(currentList[position], strikeThrough, startPencil, editTask)

    }

}

class TaskHolder(private val binding: TaskItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(
        task: TaskModel,
        strikeThrough: (MaterialCheckBox, TaskModel) -> Unit,
        startPencil: () -> Unit,
        editTask: (TaskModel) -> Unit
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

