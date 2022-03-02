package com.exampleone.todolist.presentation

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.exampleone.todolist.R
import com.exampleone.todolist.data.Database
import com.exampleone.todolist.databinding.ActivityMainBinding
import com.exampleone.todolist.models.TaskModel
import com.exampleone.todolist.repositories.TaskRepository
import com.exampleone.todolist.tabs.TaskAdapter
import com.google.android.material.checkbox.MaterialCheckBox
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class MainActivity : AppCompatActivity(),View.OnClickListener  {

    private var taskRepository: TaskRepository? = null
    private var taskViewModel: TaskViewModel? = null
    private var taskFactory: TaskFactory? = null
    private var taskAdapter: TaskAdapter? = null

    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val tasksDao = Database.getInstance(application).taskDAO

        taskRepository = TaskRepository(tasksDao)
        taskFactory = TaskFactory(taskRepository!!)
        taskViewModel = ViewModelProvider(this, taskFactory!!)[TaskViewModel::class.java]

        initRecyclerTasks()
        displayTasks()
        // startAnimation()

        binding?.fab?.setOnClickListener(this)
        binding?.bottomBarFAB?.setOnMenuItemClickListener { menuItem:MenuItem ->

        when(menuItem.itemId) {
                R.id.showFab -> {
                    if (binding!!.fab.isVisible) {
                        binding!!.fab.visibility = View.GONE
                    } else binding!!.fab.visibility = View.VISIBLE
                    true
                }

                R.id.settings -> {
                    true
                }
                R.id.help -> {
                    true
                }
                R.id.clear -> {

        val builder = MaterialAlertDialogBuilder(this, R.style.MyDialogTheme)
                .setMessage(resources.getString(R.string.messageDialog))

                .setNeutralButton(resources.getString(R.string.close)) { dialog, which ->
                        }
                .setPositiveButton(resources.getString(R.string.ok)) { dialog, which ->
                            taskViewModel?.deleteAll()
                        }
                .show()

                    builder.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(ContextCompat.getColor (this,
                        R.color.black
                    ))
                    builder.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(ContextCompat.getColor (this,
                        R.color.black
                    ))
                    builder.getButton(AlertDialog.BUTTON_NEUTRAL).setTextColor(ContextCompat.getColor (this,
                        R.color.black
                    ))

                    true
                }
                else -> false
            }
        }
    }
    private fun initRecyclerTasks(){
        binding?.recyclerTodoList?.layoutManager = LinearLayoutManager(this)
        taskAdapter = TaskAdapter ({
                taskModel: TaskModel -> deleteTask(taskModel)},
            {
                    nameT: MaterialCheckBox, taskModel:TaskModel ->
                strikeThrough(nameT,taskModel)
            },{startPencil()})

           binding?.recyclerTodoList?.adapter = taskAdapter

    }

    private fun displayTasks(){
        taskViewModel?.tasks?.observe(this, Observer {
            taskAdapter?.setList(it)
            taskAdapter?.notifyDataSetChanged()
        })
    }
    private fun deleteTask(taskModel:TaskModel) {
        taskViewModel?.delete(taskModel)
    }

    override fun onClick(view: View?) {

        when (view?.id) {

            R.id.fab -> supportFragmentManager.beginTransaction().replace(R.id.contentAddText, Add())
                .commit()
        }
    }
    private fun strikeThrough(nameT: MaterialCheckBox,taskModel:TaskModel){
        taskViewModel?.updateTask(taskModel.copy(isDone = nameT.isChecked))
    }

    private fun startPencil(){
        val pencil = MediaPlayer.create(this, R.raw.pencil1)
        pencil.start()
}
    /*private fun startAnimation(){
        YoYo.with(Techniques.Swing)
            .duration(1000)
            .repeat(0)
            .playOn(binding?.AppNameText)
    }
*/
}
