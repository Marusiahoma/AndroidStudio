package ru.mirea.pinashiname.systemintentsapp

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClickCall(view: View?) {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.CALL_PHONE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.CALL_PHONE),
                REQUEST_CALL_PHONE
            )
        } else {
            makeCall()
        }
    }

    fun onClickOpenBrowser(view: View?) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.setData(Uri.parse("http://developer.android.com"))
        startActivity(intent)
    }

    fun onClickOpenMaps(view: View?) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.setData(Uri.parse("geo:55.749479,37.613944"))
        startActivity(intent)
    }

    private fun makeCall() {
        val intent = Intent(Intent.ACTION_DIAL)
        intent.setData(Uri.parse("tel:89811112233"))
        startActivity(intent)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CALL_PHONE) {
            if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                makeCall()
            }
        }
    }

    companion object {
        private const val REQUEST_CALL_PHONE = 1
    }
}



