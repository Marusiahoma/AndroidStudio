package ru.mirea.pinashiname.intentfilter

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var buttonOpenBrowser: Button
    private lateinit var buttonShareInfo: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonOpenBrowser = findViewById(R.id.button_open_browser)
        buttonShareInfo = findViewById(R.id.button_share_info)

        buttonOpenBrowser.setOnClickListener {
            openWebPage("https://www.mirea.ru/")
        }

        buttonShareInfo.setOnClickListener {
            shareStudentInfo("ФАМИЛИЯ ИМЯ ОТЧЕСТВО")
        }
    }

    private fun openWebPage(url: String) {
        val webpage: Uri = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, webpage)
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }

    private fun shareStudentInfo(studentInfo: String) {
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.type = "text/plain"
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "MIREA")
        shareIntent.putExtra(Intent.EXTRA_TEXT, studentInfo)
        startActivity(Intent.createChooser(shareIntent, "МОИ ФИО"))
    }
}
