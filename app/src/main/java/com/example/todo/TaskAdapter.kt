package com.example.todo

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class TaskAdapter (private var task: List<Task>, context: Context) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    private val db : TaskDatabase = TaskDatabase(context)

    class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val titleTask: TextView = itemView.findViewById(R.id.titleTask)
        val contentView: TextView = itemView.findViewById(R.id.contentView)
        val updateBtn: ImageView = itemView.findViewById(R.id.updateBtn)
        val deleteBtn: ImageView = itemView.findViewById(R.id.deleteBtn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.task_item, parent, false)
        return TaskViewHolder(view)
    }

    override fun getItemCount(): Int = task.size

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = task[position]
        holder.titleTask.text = task.title
        holder.contentView.text = task.description

        holder.updateBtn.setOnClickListener {
            val intent = Intent(holder.itemView.context, UpdateTask::class.java).apply {
                putExtra("task_id", task.id)
            }
            holder.itemView.context.startActivity(intent)
        }

        holder.deleteBtn.setOnClickListener {
            db.deleteTask(task.id)
            refreshData(db.getAllTasks())
            Toast.makeText(holder.itemView.context, "Task Deleted", Toast.LENGTH_SHORT).show()
        }

    }

    fun refreshData(newTask: List<Task>){
        task = newTask
        notifyDataSetChanged()
    }

}