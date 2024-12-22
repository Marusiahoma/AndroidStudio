package com.example.resultapifragmentapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity(), FirstFragment.OnDataPassListener {

    private lateinit var secondFragment: SecondFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Загружаем оба фрагмента
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_first, FirstFragment())
            .commit()

        secondFragment = SecondFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_second, secondFragment)
            .commit()
    }

    override fun onDataPass(data: String) {
        secondFragment.updateText(data) // Передаём данные во второй фрагмент
    }
}

