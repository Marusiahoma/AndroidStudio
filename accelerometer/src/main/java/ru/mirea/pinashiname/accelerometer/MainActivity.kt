package ru.mirea.pinashiname.accelerometer

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.mirea.pinashiname.accelerometer.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(), SensorEventListener {
    private var sensorManager: SensorManager? = null
    private var accelerometrSensor: Sensor? = null
    private var binding: ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.getRoot())
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        accelerometrSensor = sensorManager!!.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
    }

    override fun onPause() {
        super.onPause()
        sensorManager!!.unregisterListener(this)
    }

    override fun onResume() {
        super.onResume()
        sensorManager!!.registerListener(
            this, accelerometrSensor,
            SensorManager.SENSOR_DELAY_NORMAL
        )
    }

    override fun onSensorChanged(event: SensorEvent) {
        if (event.sensor.type == Sensor.TYPE_ACCELEROMETER) {
            val valueAzimuth = event.values[0]
            val valuePitch = event.values[1]
            val valueRoll = event.values[2]
            binding!!.textViewAzimuth.setText("Azimuth: $valueAzimuth")
            binding!!.textViewPitch.setText("Pitch: $valuePitch")
            binding!!.textViewRoll.setText("Roll: $valueRoll")
        }
    }

    override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {}
}