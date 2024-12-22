package com.example.retrofitapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class TodoAdapter(private val todos: List<Todo>, private val onCheckedChange: (Todo) -> Unit) :
    RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    inner class TodoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.todo_title)
        val completed: CheckBox = view.findViewById(R.id.todo_completed)
        val imageView: ImageView = view.findViewById(R.id.todo_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.todo_item, parent, false)
        return TodoViewHolder(view)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val todo = todos[position]
        holder.title.text = todo.title
        holder.completed.isChecked = todo.completed

        // Загрузка изображения с помощью Picasso
        Picasso.get()
            .load("https://images.pexels.com/photos/459225/pexels-photo-459225.jpeg")
            .resize(100, 100)
            .centerCrop()
            .into(holder.imageView)

        // Обработка изменения состояния CheckBox
        holder.completed.setOnCheckedChangeListener { _, isChecked ->
            todo.completed = isChecked
            onCheckedChange(todo) // Обновление статуса в модели
        }
    }

    override fun getItemCount(): Int = todos.size
}






