package ru.mirea.pinashiname.serviceapp

import android.Manifest.permission
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import ru.mirea.pinashiname.serviceapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null
    private val TAG = "MUSIC"
    private val PermissionCode = 200
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.getRoot())
        if (ContextCompat.checkSelfPermission(this, permission.POST_NOTIFICATIONS) ==
            PackageManager.PERMISSION_GRANTED
        ) {
            Log.d(TAG, "Разрешения получены")
        } else {
            Log.d(TAG, "Нет разрешений")
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    permission.POST_NOTIFICATIONS,
                    permission.FOREGROUND_SERVICE_MEDIA_PLAYBACK
                ),
                PermissionCode
            )
        }
    }

    fun onClickPlay(view: View?) {
        val serviceIntent = Intent(
            this@MainActivity,
            PlayService::class.java
        )
        ContextCompat.startForegroundService(this@MainActivity, serviceIntent)
    }

    fun onClickPause(view: View?) {
        stopService(Intent(this@MainActivity, PlayService::class.java))
    }
}