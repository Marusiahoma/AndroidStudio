package ru.mirea.pinashiname.serviceapp

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat


class PlayService : Service() {
    private var mediaPlayer: MediaPlayer? = null
    override fun onBind(intent: Intent): IBinder? {
        throw UnsupportedOperationException("Not yet implemented")
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        mediaPlayer!!.start()
        mediaPlayer!!.setOnCompletionListener { stopForeground(true) }
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onCreate() {
        super.onCreate()
        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentText("Playing...")
            .setSmallIcon(R.mipmap.ic_launcher)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText("The Space")
            )
            .setContentTitle("Music player")
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(CHANNEL_ID, "Student ", importance)
        channel.description = "MIREA channel"
        val notificationManager = NotificationManagerCompat.from(this)
        notificationManager.createNotificationChannel(channel)
        startForeground(1, builder.build())
        mediaPlayer = MediaPlayer.create(this, R.raw.music)
        mediaPlayer!!.isLooping = false
    }

    override fun onDestroy() {
        super.onDestroy()
        stopForeground(true)
        mediaPlayer!!.stop()
    }

    companion object {
        const val CHANNEL_ID = "ForegroundServiceChannel"
    }
}