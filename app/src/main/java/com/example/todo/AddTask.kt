package com.example.todo

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.todo.databinding.ActivityTaskBinding

class AddTask : AppCompatActivity() {

    private lateinit var binding: ActivityTaskBinding
    private lateinit var db: TaskDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTaskBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_task)

        db = TaskDatabase(this)

        binding.saveBtn.setOnClickListener {
            val title = binding.editTitle.text.toString()
            val description = binding.editContent.text.toString()
            val task = Task(0, title, description)
            db.insertTask(task)
            finish()
            Toast.makeText(this, "Task Saved", Toast.LENGTH_SHORT).show()
        }
    }
}