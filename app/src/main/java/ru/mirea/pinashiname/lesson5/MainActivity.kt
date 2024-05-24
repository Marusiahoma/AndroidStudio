package ru.mirea.pinashiname.lesson5

import android.hardware.Sensor
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.ListView
import android.widget.SimpleAdapter
import androidx.appcompat.app.AppCompatActivity
import ru.mirea.pinashiname.lesson5.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.getRoot())
        val sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        val sensors = sensorManager.getSensorList(Sensor.TYPE_ALL)
        val listSensor: ListView = binding!!.sensorList

        // создаем список для отображения в ListView найденных датчиков
        val arrayList = ArrayList<HashMap<String, Any?>>()
        for (i in sensors.indices) {
            val sensorTypeList = HashMap<String, Any?>()
            sensorTypeList["NAME"] = sensors[i].name
            sensorTypeList["VALUE"] = sensors[i].maximumRange
            arrayList.add(sensorTypeList)
        }
        // создаем адаптер и устанавливаем тип адаптера - отображение двух полей
        val simpleAdapter = SimpleAdapter(
            this,
            arrayList,
            android.R.layout.simple_list_item_2,
            arrayOf("NAME", "VALUE"),
            intArrayOf(android.R.id.text1, android.R.id.text2)
        )
        listSensor.adapter = simpleAdapter
    }
}