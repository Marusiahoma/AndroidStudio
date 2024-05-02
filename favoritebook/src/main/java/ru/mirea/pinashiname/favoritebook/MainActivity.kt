package ru.mirea.pinashiname.favoritebook

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bookTextView: TextView = findViewById(R.id.bookTextView)
        val openSecondActivityButton: Button = findViewById(R.id.openSecondActivityButton)

        openSecondActivityButton.setOnClickListener {
            val intent = Intent(this, ShareActivity::class.java)
            startActivityForResult(intent, 1)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                val bookName = data?.getStringExtra("bookName")
                val bookTextView: TextView = findViewById(R.id.bookTextView)
                bookTextView.text = "Название Вашей любимой книги: $bookName"
            }
        }
    }
}
