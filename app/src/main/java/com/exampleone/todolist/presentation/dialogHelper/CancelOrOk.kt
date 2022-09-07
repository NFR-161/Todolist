package com.exampleone.todolist.presentation.dialogHelper

import android.app.AlertDialog
import android.app.Application
import android.content.Context
import androidx.core.content.ContextCompat
import com.exampleone.todolist.R
import com.exampleone.todolist.presentation.viewmodels.TaskViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import javax.inject.Inject

class CancelOrOk @Inject constructor() {

      fun cancelOrOk(taskViewModel:TaskViewModel,context: Context): Boolean {
        val builder = MaterialAlertDialogBuilder(context, R.style.MyDialogTheme)
            .setMessage(context.resources.getString(R.string.messageDialog))
            .setNeutralButton(context.resources.getString(R.string.close)) { dialog, which ->
            }
            .setPositiveButton(context.resources.getString(R.string.ok)) { dialog, which ->
                taskViewModel.deleteAll()
            }
            .show()
        builder.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(
            ContextCompat.getColor(
                context,
                R.color.black
            )
        )
        builder.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(
            ContextCompat.getColor(
                context,
                R.color.black
            )
        )
        builder.getButton(AlertDialog.BUTTON_NEUTRAL).setTextColor(
            ContextCompat.getColor(
                context,
                R.color.black
            )
        )
        return true
    }
}