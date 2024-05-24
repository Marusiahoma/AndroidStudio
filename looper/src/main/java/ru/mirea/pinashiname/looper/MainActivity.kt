package ru.mirea.pinashiname.looper

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import ru.mirea.pinashiname.looper.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private val TAG = "lesson 4 Main"
    private var binding: ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.getRoot())
        val mainThreadHandler: Handler = object : Handler(Looper.getMainLooper()) {
            override fun handleMessage(msg: Message) {
                Log.d(TAG, msg.data.getString("RESULT")!!)
                Log.d(TAG, msg.data.getString("DATA")!!)
            }
        }
        val myLooper = MyLooper(mainThreadHandler)
        myLooper.start()
        binding!!.text.setText("Мой номер по списку № 20")
        binding!!.button.setOnClickListener(View.OnClickListener {
            val msg = Message.obtain()
            val bundle = Bundle()
            bundle.putString("AGE", binding!!.editTextAge.getText().toString())
            bundle.putString("WORK", binding!!.editTextWork.getText().toString())
            bundle.putLong("TIME", System.currentTimeMillis())
            msg.data = bundle
            myLooper.mHandler?.sendMessage(msg)
        })
    }
}