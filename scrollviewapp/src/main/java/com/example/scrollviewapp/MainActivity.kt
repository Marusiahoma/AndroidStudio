package com.example.scrollviewapp

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        // Получаем контейнер LinearLayout из разметки
        val container = findViewById<LinearLayout>(R.id.container)

        // Задаем начальный элемент и знаменатель
        var element = 1L // Первый элемент прогрессии
        val ratio = 2L   // Знаменатель прогрессии

        // Генерируем и добавляем 100 элементов
        for (i in 1..100) {
            val textView = TextView(this).apply {
                text = "Элемент $i: $element"
                textSize = 16f
                setPadding(0, 8, 0, 8)
            }
            container.addView(textView)
            element *= ratio // Умножаем на знаменатель
        }
    }
}