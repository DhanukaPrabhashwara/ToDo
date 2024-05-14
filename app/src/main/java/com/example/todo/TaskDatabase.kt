package com.example.todo

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class TaskDatabase (context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){

    companion object{
        private const val DATABASE_NAME = "ToDoapp.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "usertasks"
        private const val COLUMN_ID = "id"
        private const val COLUMN_TITLE = "title"
        private const val COLUMN_CONTENT = "description"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery = "CREATE TABLE $TABLE_NAME ($COLUMN_ID INTEGER PRIMARY KEY, $COLUMN_TITLE TEXT, $COLUMN_CONTENT TEXT)"
        db?.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val dropTableQuery = "DROP TABLE IF EXISTS $TABLE_NAME"
        db?.execSQL(dropTableQuery)
        onCreate(db)
    }

    fun insertTask(task: Task){
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_TITLE, task.title)
            put(COLUMN_CONTENT, task.description)
        }
        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun getAllTasks(): List<Task> {
        val taskList = mutableListOf<Task>()
        val db = readableDatabase
        val query = "SELECT * FROM $TABLE_NAME"
        val cursor = db.rawQuery(query, null)

        while (cursor.moveToNext()){
            val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID))
            val title = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE))
            val description = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CONTENT))

            val task = Task(id, title, description)
            taskList.add(task)
        }
        cursor.close()
        db.close()
        return taskList

    }

    fun updateTask(task: Task){
        val db = writableDatabase
        val values = ContentValues().apply{
            put(COLUMN_TITLE, task.title)
            put(COLUMN_CONTENT, task.description)
        }
        val whereClause = "$COLUMN_ID = ?"
        val whereArgs = arrayOf(task.id.toString())
        db.update(TABLE_NAME, values, whereClause, whereArgs)
        db.close()
    }

    fun getTaskByID(taskId: Int): Task{
        val db = readableDatabase
        val query = "SELECT * FROM $TABLE_NAME WHERE $COLUMN_ID = $taskId"
        val cursor = db.rawQuery(query, null)
        cursor.moveToFirst()

        val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID))
        val title = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE))
        val description = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CONTENT))

        cursor.close()
        db.close()
        return Task(id, title, description)
    }

}









