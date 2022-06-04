package com.exampleone.todolist.presentation

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.exampleone.todolist.presentation.adapter.TaskAdapter
import com.exampleone.todolist.presentation.viewmodels.TaskViewModel
import javax.inject.Inject

class SwipeDelete @Inject constructor() {
      fun setupSwipeListener(
        rvTaskList: RecyclerView,
        taskViewModel: TaskViewModel,
        taskAdapter: TaskAdapter
    ) {
        val callback = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = taskAdapter.currentList[viewHolder.adapterPosition]
                if (item != null) {
                    taskViewModel.delete(item)
                }
            }
        }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(rvTaskList)
    }
}