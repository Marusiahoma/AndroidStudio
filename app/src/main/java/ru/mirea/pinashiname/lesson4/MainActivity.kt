package ru.mirea.pinashiname.lesson4

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import ru.mirea.pinashiname.lesson4.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null
    private var isPlay = true
    var Music = arrayOf("Песня 1", "Песня 2", "Песня 3")
    var i = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        binding!!.textView.setText(Music[0])
    }

    fun onPlay(view: View?) {
        isPlay = if (isPlay) {
            binding!!.play.setImageResource(R.drawable.ic_launcher_foreground)
            false
        } else {
            binding!!.play.setImageResource(R.drawable.ic_launcher_foreground)
            true
        }
    }

    fun onNext(view: View?) {
        i++
        if (i >= Music.size) {
            i = 0
        }
        binding!!.textView.setText(Music[i])
    }

    fun onBack(view: View?) {
        i--
        if (i < 0) {
            i = Music.size - 1
        }
        binding!!.textView.setText(Music[i])
    }
}