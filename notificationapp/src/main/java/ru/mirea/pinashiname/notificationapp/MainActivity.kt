package ru.mirea.pinashiname.notificationapp

import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import android.widget.Button

class MainActivity : AppCompatActivity() {

    private val CHANNEL_ID = "channel_id"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Находим кнопку по идентификатору
        val button = findViewById<Button>(R.id.button_open_notification)

        // Устанавливаем обработчик нажатия на кнопку
        button.setOnClickListener {
            onClickSendNotification()
        }
    }

    private fun onClickSendNotification() {
        try {
            // Создаем канал уведомлений (требуется для версий Android 8 и выше)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val importance = NotificationManager.IMPORTANCE_DEFAULT
                val channel =
                    NotificationChannel(CHANNEL_ID, "Student FIO Notification", importance)
                channel.description = "MIREA Channel"
                val notificationManager = getSystemService(NotificationManager::class.java)
                notificationManager.createNotificationChannel(channel)
            }

            // Создаем уведомление с указанием значка
            val builder = NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_notification_icon)
                .setContentText("Congratulation!")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setStyle(
                    NotificationCompat.BigTextStyle()
                        .bigText("Much longer text that cannot fit one line...")
                )
                .setContentTitle("Mirea")

            // Отправляем уведомление
            val notificationManager = NotificationManagerCompat.from(this)
            notificationManager.notify(1, builder.build())
        } catch (e: SecurityException) {
            e.printStackTrace()
        }
    }
}





