package ru.mirea.pinashiname.thread

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import ru.mirea.pinashiname.thread.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.getRoot())
    }

    fun onClick(view: View?) {
        Thread(Runnable
        // фоновый процесс
        {
            if (binding!!.editTextPair.length() > 0 && binding!!.editTextMonth.length() > 0) {
                val pair: Int = binding!!.editTextPair.getText().toString().toInt()
                val month: Int = binding!!.editTextMonth.getText().toString().toInt()
                try {
                    Thread.sleep((5 * 1000).toLong())
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
                if (month == 0) {
                    runOnUiThread { binding!!.textView.setText("Число дней не может быть равно 0") }
                    return@Runnable
                }
                val i = Math.round(pair.toDouble() / month).toInt()

                // runOnUiThread(() ->   ибо нельза из фона обновить ui
                runOnUiThread { binding!!.textView.setText("Среднее число пар в день = $i") }
            } else {
                runOnUiThread { binding!!.textView.setText("Заполните все поля") }
            }
        }).start()
    }
}