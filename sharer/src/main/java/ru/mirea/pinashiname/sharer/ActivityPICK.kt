package ru.mirea.pinashiname.sharer

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.appcompat.app.AppCompatActivity

class ActivityPICK : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pick)
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "*/*"
        val callback = registerForActivityResult(StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val data = result.data
                Log.d(MainActivity::class.java.simpleName, "Data" + data?.dataString)
            }
        }
        callback.launch(intent)
    }

    fun onBack(view: View?) {
        startActivity(Intent(this@ActivityPICK, MainActivity::class.java))
    }
}
