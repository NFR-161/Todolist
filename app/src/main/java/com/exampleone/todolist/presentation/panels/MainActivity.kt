package com.exampleone.todolist.presentation.panels

import android.media.MediaPlayer
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.exampleone.todolist.R
import com.exampleone.todolist.databinding.ActivityMainBinding
import com.exampleone.todolist.domain.TaskItem
import com.exampleone.todolist.di.TaskApp
import com.exampleone.todolist.presentation.CancelOrOk
import com.exampleone.todolist.presentation.SwipeDelete
import com.exampleone.todolist.presentation.viewmodels.TaskFactory
import com.exampleone.todolist.presentation.viewmodels.TaskViewModel
import com.exampleone.todolist.presentation.adapter.TaskAdapter
import com.google.android.material.checkbox.MaterialCheckBox
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import javax.inject.Inject


class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var taskViewModel: TaskViewModel
    private lateinit var taskAdapter: TaskAdapter

    @Inject
    lateinit var swipeDelete: SwipeDelete

    @Inject
    lateinit var cancelOrOk: CancelOrOk

    @Inject
    lateinit var taskFactory: TaskFactory
    lateinit var binding: ActivityMainBinding

    private val component by lazy {
        (application as TaskApp).component
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        taskViewModel = ViewModelProvider(this, taskFactory)[TaskViewModel::class.java]

        initRecyclerTasks()
        displayTasks()
        // startAnimation()

        binding.fab.setOnClickListener(this)
        tapMenu()
    }

    private fun initRecyclerTasks() {
        taskAdapter = TaskAdapter(
            { nameT, taskItem -> strikeThrough(nameT, taskItem) },
            { startPencil() },
            { taskItem -> editTask(taskItem) }
        )
        binding.recyclerTodoList.adapter = taskAdapter
        binding.recyclerTodoList.itemAnimator = null
        swipeDelete.setupSwipeListener(binding.recyclerTodoList, taskViewModel, taskAdapter)

    }

    private fun displayTasks() {
        taskViewModel.tasks.observe(this, Observer {
            taskAdapter.submitList(it)
        })
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.fab -> launchFragmentAdd()
        }
    }

    private fun strikeThrough(nameT: MaterialCheckBox, taskItem: TaskItem) {
        taskViewModel.updateTask(taskItem.copy(isDone = nameT.isChecked))
    }

    private fun startPencil() {
        val pencil = MediaPlayer.create(this, R.raw.pencil1)
        pencil.start()
    }

    private fun editTask(taskItem: TaskItem) {
        val panelEditTask = PanelEditTask()
        val parameters = Bundle()
        parameters.putString("idTask", taskItem.id.toString())
        parameters.putString("nameTask", taskItem.name)
        panelEditTask.arguments = parameters
        panelEditTask.show(supportFragmentManager, "editTask")
    }

    private fun launchFragmentAdd() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.contentAddText, Add.newInstance()).addToBackStack(null).commit()
    }

    private fun tapMenu() {
        binding.bottomBarFAB.setOnMenuItemClickListener { menuItem: MenuItem ->

            when (menuItem.itemId) {
                R.id.showFab -> {
                    if (binding.fab.isVisible) {
                        binding.fab.visibility = View.GONE
                    } else binding.fab.visibility = View.VISIBLE
                    true
                }

                R.id.settings -> {
                    true
                }
                R.id.help -> {
                    true
                }
                R.id.clear -> cancelOrOk.cancelOrOk(taskViewModel, this)
                else -> false
            }
        }
    }
}

