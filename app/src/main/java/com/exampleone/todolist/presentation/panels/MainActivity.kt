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
import com.exampleone.todolist.data.TaskModel
import com.exampleone.todolist.databinding.ActivityMainBinding
import com.exampleone.todolist.presentation.TaskApp
import com.exampleone.todolist.presentation.TaskFactory
import com.exampleone.todolist.presentation.TaskViewModel
import com.exampleone.todolist.presentation.adapter.TaskAdapter
import com.google.android.material.checkbox.MaterialCheckBox
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import javax.inject.Inject


class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var taskViewModel: TaskViewModel
    private var taskAdapter: TaskAdapter? = null

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
                R.id.clear -> cancelOrOk()
                else -> false
            }
        }
    }

    private fun initRecyclerTasks() {
        taskAdapter = TaskAdapter(
            { nameT, taskModel -> strikeThrough(nameT, taskModel) },
            { startPencil() },
            { taskModel -> editTask(taskModel) }
        )
        binding.recyclerTodoList.adapter = taskAdapter
        binding.recyclerTodoList.itemAnimator = null
        setupSwipeListener(binding.recyclerTodoList)

    }

    private fun displayTasks() {
        taskViewModel.tasks.observe(this, Observer {
            taskAdapter?.submitList(it)
        })
    }

    private fun deleteTask(taskModel: TaskModel) {
        taskViewModel.delete(taskModel)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.fab -> launchFragmentAdd()
        }
    }

    private fun strikeThrough(nameT: MaterialCheckBox, taskModel: TaskModel) {
        taskViewModel.updateTask(taskModel.copy(isDone = nameT.isChecked))
    }

    private fun startPencil() {
        val pencil = MediaPlayer.create(this, R.raw.pencil1)
        pencil.start()
    }

    private fun setupSwipeListener(rvTaskList: RecyclerView) {
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
                val item = taskAdapter?.currentList?.get(viewHolder.adapterPosition)
                if (item != null) {
                    deleteTask(item)
                }
            }
        }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(rvTaskList)
    }

    private fun editTask(taskModel: TaskModel) {
        val panelEditTask = PanelEditTask()
        val parameters = Bundle()
        parameters.putString("idTask", taskModel.id.toString())
        parameters.putString("nameTask", taskModel.name)
        panelEditTask.arguments = parameters
        panelEditTask.show(supportFragmentManager, "editTask")
    }

    private fun launchFragmentAdd() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.contentAddText, Add.newInstance()).addToBackStack(null).commit()
    }

    private fun cancelOrOk(): Boolean {
        val builder = MaterialAlertDialogBuilder(this, R.style.MyDialogTheme)
            .setMessage(resources.getString(R.string.messageDialog))
            .setNeutralButton(resources.getString(R.string.close)) { dialog, which ->
            }
            .setPositiveButton(resources.getString(R.string.ok)) { dialog, which ->
                taskViewModel.deleteAll()
            }
            .show()
        builder.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(
            ContextCompat.getColor(
                this,
                R.color.black
            )
        )
        builder.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(
            ContextCompat.getColor(
                this,
                R.color.black
            )
        )
        builder.getButton(AlertDialog.BUTTON_NEUTRAL).setTextColor(
            ContextCompat.getColor(
                this,
                R.color.black
            )
        )
        return true
    }
}

