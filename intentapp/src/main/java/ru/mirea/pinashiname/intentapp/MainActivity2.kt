package ru.mirea.pinashiname.intentapp

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val number = intent.getIntExtra("number", 0)
        val currentTime = intent.getStringExtra("currentTime")

        val textView: TextView = findViewById(R.id.textView)
        val message = "КВАДРАТ ЗНАЧЕНИЯ МОЕГО НОМЕРА ПО СПИСКУ В ГРУППЕ СОСТАВЛЯЕТ $number, а текущее время $currentTime"
        textView.text = message
    }
}
