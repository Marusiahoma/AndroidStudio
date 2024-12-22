package com.example.fragmentapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            // Создаем первый фрагмент
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_1, TodoFragment())
                .commit()

            // Создаем второй фрагмент и передаем данные
            val secondFragment = SecondFragment()
            val bundle = Bundle()
            bundle.putInt("item_number", 14) // Передаем номер
            secondFragment.arguments = bundle

            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_2, secondFragment)
                .commit()
        }
    }
}

