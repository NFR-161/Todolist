package com.exampleone.todolist.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.exampleone.todolist.domain.TaskModel


@Database(entities = [TaskModel::class], version = 1)
abstract class Database : RoomDatabase() {

    abstract val taskDAO: TaskDao


    companion object {
        @Volatile
        private var INSTANCE: com.exampleone.todolist.data.Database? = null
        fun getInstance(context: Context): com.exampleone.todolist.data.Database {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        com.exampleone.todolist.data.Database::class.java,
                        "database"
                    ).build()
                }
                return instance
            }
        }

    }
}