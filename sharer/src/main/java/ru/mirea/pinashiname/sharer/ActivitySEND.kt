package ru.mirea.pinashiname.sharer

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity


class ActivitySEND : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_send)
        val intent = Intent(Intent.ACTION_SEND)
        intent.setType("*/*")
        intent.putExtra(Intent.EXTRA_TEXT, "MIREA")
        startActivity(Intent.createChooser(intent, "Выбор за Вами!"))
    }

    fun onBack(view: View?) {
        startActivity(Intent(this@ActivitySEND, MainActivity::class.java))
    }
}