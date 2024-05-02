package ru.mirea.pinashiname.favoritebook

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class ShareActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_share)

        val bookEditText: EditText = findViewById(R.id.bookEditText)
        val sendButton: Button = findViewById(R.id.sendButton)

        sendButton.setOnClickListener {
            val bookName = bookEditText.text.toString()
            val resultIntent = Intent()
            resultIntent.putExtra("bookName", bookName)
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }
}
