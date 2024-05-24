package ru.mirea.pinashiname.looper

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log


class MyLooper(private val mainHandler: Handler) : Thread() {
    private val TAG = "lesson 4 MyLooper"
    var mHandler: Handler? = null
    override fun run() {
        Log.d(TAG, "run loop")
        Looper.prepare()
        mHandler = object : Handler(Looper.myLooper()!!) {
            override fun handleMessage(msg: Message) {
                val dataAge = msg.data.getString("AGE")
                val dataWork = msg.data.getString("WORK")
                val dataTime = msg.data.getLong("TIME")
                val count = System.currentTimeMillis() - dataTime
                val message = Message()
                val bundle = Bundle()
                bundle.putString("RESULT", String.format("Результат вычисления задержки %d", count))
                bundle.putString(
                    "DATA",
                    String.format("Возраст: %s, Работа: %s", dataAge, dataWork)
                )
                message.data = bundle
                mainHandler.sendMessage(message)
            }
        }
        Looper.loop()
    }
}