package com.example.todo

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.todo.databinding.ActivityUpdateTaskBinding

class UpdateTask : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateTaskBinding
    private lateinit var db: TaskDatabase
    private var taskId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityUpdateTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = TaskDatabase(this)

        taskId = intent.getIntExtra("task_id", -1)
        if (taskId == -1){
            finish()
            return
        }

        val task = db.getTaskByID(taskId)
        binding.updateTitle.setText(task.title)
        binding.updateContent.setText(task.description)

//      save edited data when click the button
        binding.updateSaveBtn.setOnClickListener {
            val newTitle = binding.updateTitle.text.toString()
            val newDescription = binding.updateContent.text.toString()
            val updatedTask = Task(taskId, newTitle, newDescription)

            db.updateTask(updatedTask)
            finish()
            Toast.makeText(this, "Saved changes", Toast.LENGTH_SHORT).show()
        }

    }
}





