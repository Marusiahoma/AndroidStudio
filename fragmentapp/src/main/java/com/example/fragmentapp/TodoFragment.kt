package com.example.fragmentapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TodoFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var todoAdapter: TodoAdapter
    private lateinit var todos: MutableList<Todo>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_todo, container, false)

        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(activity)

        todos = mutableListOf()
        todoAdapter = TodoAdapter(todos) { updatedTodo ->
            updateTodoStatus(updatedTodo)
        }

        recyclerView.adapter = todoAdapter

        loadTodos()

        return view
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
                Toast.makeText(activity, "Error loading todos", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun updateTodoStatus(todo: Todo) {
        RetrofitInstance.api.updateTodoStatus(todo.id, todo).enqueue(object : Callback<Todo> {
            override fun onResponse(call: Call<Todo>, response: Response<Todo>) {
                if (response.isSuccessful) {
                    Toast.makeText(activity, "Todo status updated", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Todo>, t: Throwable) {
                Toast.makeText(activity, "Error updating todo status", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
