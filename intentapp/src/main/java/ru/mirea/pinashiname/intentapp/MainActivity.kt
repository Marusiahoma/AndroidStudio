package ru.mirea.pinashiname.intentapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button: Button = findViewById(R.id.button)
        button.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            val number = 400
            intent.putExtra("number", number)

            val dateInMillis = System.currentTimeMillis()
            val format = "yyyy-MM-dd HH:mm:ss"
            val sdf = SimpleDateFormat(format, Locale.getDefault())
            val dateString = sdf.format(Date(dateInMillis))
            intent.putExtra("currentTime", dateString)

            startActivity(intent)
        }
    }
}


