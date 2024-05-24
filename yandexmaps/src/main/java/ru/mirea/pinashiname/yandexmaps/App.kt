package ru.mirea.pinashiname.yandexmaps

import android.app.Application
import com.yandex.mapkit.MapKitFactory

class App : Application() {

    companion object {
        private const val MAPKIT_API_KEY = "a44e6c7f-0ef3-4160-836a-4af1ee304664"
    }

    override fun onCreate() {
        super.onCreate()

        MapKitFactory.setApiKey(MAPKIT_API_KEY)
    }
}