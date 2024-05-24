package ru.mirea.pinashiname.audiorecord

import android.Manifest
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import ru.mirea.pinashiname.audiorecord.databinding.ActivityMainBinding
import java.io.File
import java.io.IOException


class MainActivity : AppCompatActivity() {
    private val TAG = "Record"
    private var isWork = false
    private var isStartPlaying = true
    private var isStartRecording = true
    private var recordFilePath: String? = null
    private var recordButton: Button? = null
    private var playButton: Button? = null
    private var player: MediaPlayer? = null
    private var recorder: MediaRecorder? = null
    private var binding: ActivityMainBinding? = null

    // включить 1 и 3 переключатель в настройках микрофона (3 точки над телефоном)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.getRoot())
        recordButton = binding!!.buttonRec
        playButton = binding!!.buttonPlay
        playButton!!.isEnabled = false
        recordFilePath = File(
            getExternalFilesDir(Environment.DIRECTORY_MUSIC),
            "/audiorecordtest.3gp"
        ).absolutePath
        val audioRecordPermissionStatus =
            ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)
        val storagePermissionStatus =
            ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        if (audioRecordPermissionStatus == PackageManager.PERMISSION_GRANTED &&
            storagePermissionStatus == PackageManager.PERMISSION_GRANTED
        ) {
            isWork = true
        } else {
            ActivityCompat.requestPermissions(
                this, arrayOf(
                    Manifest.permission.RECORD_AUDIO,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ), REQUEST_CODE_PERMISSION
            )
        }
        recordButton!!.setOnClickListener {
            if (isStartRecording) {
                recordButton!!.text = "Запись остановлена"
                playButton!!.isEnabled = false
                startRecording()
            } else {
                recordButton!!.text = "Начать запись"
                playButton!!.isEnabled = true
                stopRecording()
            }
            isStartRecording = !isStartRecording
        }
        playButton!!.setOnClickListener {
            if (isStartPlaying) {
                playButton!!.text = "Остановить воспроизведение"
                recordButton!!.isEnabled = false
                startPlaying()
            } else {
                playButton!!.text = "Начать воспроизведение"
                recordButton!!.isEnabled = true
                stopPlaying()
            }
            isStartPlaying = !isStartPlaying
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_PERMISSION) {
            isWork = grantResults.size > 0 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED
        }
        if (!isWork) {
            finish() //если разрешения не дали - закрываем активити
        }
    }

    private fun startRecording() {
        recorder = MediaRecorder()
        recorder!!.setAudioSource(MediaRecorder.AudioSource.MIC)
        recorder!!.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
        recorder!!.setOutputFile(recordFilePath)
        recorder!!.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)
        try {
            recorder!!.prepare()
        } catch (e: IOException) {
            Log.d(TAG, "startRecording prepare() FAIL")
        }
        recorder!!.start()
    }

    private fun stopRecording() {
        recorder!!.stop()
        recorder!!.release()
        recorder = null
    }

    private fun startPlaying() {
        player = MediaPlayer()
        try {
            player!!.setDataSource(recordFilePath)
            player!!.isLooping = true
            player!!.prepare()
            player!!.start()
        } catch (e: IOException) {
            Log.d(TAG, "startPlaying prepare() FAIL")
        }
    }

    private fun stopPlaying() {
        player!!.stop()
        player!!.release()
        player = null
    }

    companion object {
        private const val REQUEST_CODE_PERMISSION = 200
    }
}