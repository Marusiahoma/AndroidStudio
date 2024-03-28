package ru.mirea.pinashiname.button_clicker

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

        lateinit var CheckBox: CheckBox
        lateinit var textViewStudent: TextView
        lateinit var btnWhoAmI: Button
        lateinit var btnItIsNotMe: Button
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)
            textViewStudent = findViewById<TextView>(R.id.tvOut)
            btnWhoAmI = findViewById<Button>(R.id.btnWhoAmI)
            btnItIsNotMe = findViewById<Button>(R.id.btnItIsNotMe)
            CheckBox = findViewById(R.id.checkBox)
            val oclBtnWhoAmI =
                View.OnClickListener {
                    textViewStudent.setText("Мой номер по списку № 19")
                    CheckBox.toggle()
                }
            btnWhoAmI.setOnClickListener(oclBtnWhoAmI)
        }

    fun onMyButtonClick(view: View?) {
        // выводим сообщение
        Toast.makeText(this, "Ещё один способ!", Toast.LENGTH_SHORT).show()
        CheckBox.toggle()
    }
}