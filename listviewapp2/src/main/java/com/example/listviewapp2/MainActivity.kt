package com.example.listviewapp2

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)

        // Пример списка исторических событий
        val events = listOf(
            HistoricalEvent(
                "Падение Западной Римской империи",
                "476 год н.э. — конец Западной Римской империи, вызванный варварскими вторжениями.",
                R.drawable.event1
            ),
            HistoricalEvent(
                "Крещение Руси",
                "988 год н.э. — Владимир Святославич вводит христианство в качестве государственной религии.",
                R.drawable.event1
            ),
            HistoricalEvent(
                "Начало Второй мировой войны",
                "1939 год — Германия нападает на Польшу, что приводит к началу войны.",
                R.drawable.event1
            ),
            HistoricalEvent(
                "Первый полет человека в космос",
                "1961 год — Юрий Гагарин становится первым человеком, полетевшим в космос.",
                R.drawable.event1
            )
            // Добавьте более 30 событий
        )

        // Устанавливаем адаптер и менеджер компоновки для RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = HistoricalEventAdapter(events)
    }
}