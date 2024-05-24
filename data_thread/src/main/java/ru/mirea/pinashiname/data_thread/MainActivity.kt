package ru.mirea.pinashiname.data_thread

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.mirea.pinashiname.data_thread.databinding.ActivityMainBinding
import java.util.concurrent.TimeUnit


class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.getRoot())
        val runn1 = Runnable { binding!!.textView.setText("runn1 runOnUiThread") }
        val runn2 = Runnable { binding!!.textView.setText("runn2 post") }
        val runn3 = Runnable { binding!!.textView.setText("runn3 postDelayed") }
        val t = Thread {
            try {
                TimeUnit.SECONDS.sleep(2)
                runOnUiThread(runn1)
                TimeUnit.SECONDS.sleep(1)
                binding!!.textView.postDelayed(runn3, 2000)
                binding!!.textView.post(runn2)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }
        t.start()
    }
}