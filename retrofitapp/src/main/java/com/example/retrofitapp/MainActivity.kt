package com.example.retrofitapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var todoAdapter: TodoAdapter
    private lateinit var todos: MutableList<Todo>
    private lateinit var todoViewModel: TodoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        todos = mutableListOf()
        todoAdapter = TodoAdapter(todos) { updatedTodo ->
            updateTodoStatus(updatedTodo)
        }

        recyclerView.adapter = todoAdapter

        loadTodos()
    }

    private fun loadTodos() {
        RetrofitInstance.api.getTodos().enqueue(object : Callback<List<Todo>> {
            override fun onResponse(call: Call<List<Todo>>, response: Response<List<Todo>>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        todos.clear()
                        todos.addAll(it)
                        todoAdapter.notifyDataSetChanged()
                    }
                }
            }

            override fun onFailure(call: Call<List<Todo>>, t: Throwable) {
                // обработка ошибки
            }
        })
    }

    private fun updateTodoStatus(todo: Todo) {
        RetrofitInstance.api.updateTodoStatus(todo.id, todo).enqueue(object : Callback<Todo> {
            override fun onResponse(call: Call<Todo>, response: Response<Todo>) {
                if (response.isSuccessful) {
                    // Обновленный статус задачи
                }
            }

            override fun onFailure(call: Call<Todo>, t: Throwable) {
                // обработка ошибки
            }
        })
    }
}





