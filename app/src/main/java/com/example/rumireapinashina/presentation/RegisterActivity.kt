package com.example.rumireapinashina.presentation

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.data.PreferencesManager
import com.example.data.Service.AuthService
import com.example.domain.Domains.usecase.Registration
import kotlinx.coroutines.launch
import com.example.data.repository.UserRepositoryImpl
import com.example.rumireapinashina.R

class RegisterActivity : AppCompatActivity() {
    private lateinit var registrationUseCase: Registration
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var confirmPasswordEditText: EditText
    private lateinit var registerButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val preferencesManager = PreferencesManager(this)

        registrationUseCase = Registration(UserRepositoryImpl(AuthService(), preferencesManager))

        emailEditText = findViewById(R.id.emailEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        confirmPasswordEditText = findViewById(R.id.confirmPasswordEditText)
        registerButton = findViewById(R.id.registerButton)

        registerButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            val confirmPassword = confirmPasswordEditText.text.toString()

            if (isPasswordValid(password, confirmPassword)) {
                registerUser(email, password)
            } else {
                Toast.makeText(this, "Пароли не совпадают или слишком короткие", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun isPasswordValid(password: String, confirmPassword: String): Boolean {
        return password.length >= 6 && password == confirmPassword
    }

    private fun registerUser(email: String, password: String) {
        lifecycleScope.launch {
            registrationUseCase(email, password).onSuccess {
                Toast.makeText(this@RegisterActivity, "Регистрация успешна", Toast.LENGTH_SHORT).show()
                finish()
            }.onFailure {
                Toast.makeText(this@RegisterActivity, "Ошибка регистрации", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
