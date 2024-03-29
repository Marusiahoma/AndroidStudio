package ru.mirea.pinashiname.toastapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var editText: EditText
    private lateinit var countButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText = findViewById(R.id.editText)
        countButton = findViewById(R.id.countButton)

        countButton.setOnClickListener {
            countCharacters()
        }
    }

    private fun countCharacters() {
        val inputText = editText.text.toString()
        val charCount = inputText.length
        val message = "СТУДЕНТ № $charCount ГРУППА $charCount Количество символов - $charCount"
        showToast(message)
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
