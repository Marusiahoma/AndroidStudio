package com.example.rumireapinashina.presentation

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.data.PreferencesManager
import com.example.data.Service.AuthService
import com.example.domain.Domains.usecase.Login
import kotlinx.coroutines.launch
import com.example.data.repository.UserRepositoryImpl
import com.example.rumireapinashina.R

class AuthActivity : AppCompatActivity() {
    private lateinit var loginUseCase: Login
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button
    private lateinit var registerButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        loginUseCase = Login(UserRepositoryImpl(AuthService(), PreferencesManager(this)))


        emailEditText = findViewById(R.id.emailEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        loginButton = findViewById(R.id.loginButton)
        registerButton = findViewById(R.id.registerButton)

        loginButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            loginUser(email, password)
        }

        registerButton.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    private fun loginUser(email: String, password: String) {
        lifecycleScope.launch {
            loginUseCase(email, password).onSuccess {
                val preferencesManager = PreferencesManager(this@AuthActivity)
                preferencesManager.setLoggedIn(true)

                startActivity(Intent(this@AuthActivity, MainActivity::class.java))
                finish()
            }.onFailure {
                Toast.makeText(this@AuthActivity, "Ошибка авторизации", Toast.LENGTH_SHORT).show()
            }
        }
    }

}
