package com.example.rumireapinashina.presentation

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.ActionBarDrawerToggle

import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.example.data.PreferencesManager
import com.example.data.Service.AuthService
import com.example.data.repository.UserRepositoryImpl
import com.example.rumireapinashina.R
import com.example.rumireapinashina.BaseActivity


class MainActivity : BaseActivity() {

    private lateinit var toolbar: Toolbar
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference
    private lateinit var emailTextView: TextView
    private lateinit var logoutButton: Button
    private lateinit var navigationView: NavigationView
    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        setupNavigationDrawer()


        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance().reference
        emailTextView = findViewById(R.id.emailTextView)
        logoutButton = findViewById(R.id.logoutButton)
        drawerLayout = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.navigation_view)

        val preferencesManager = PreferencesManager(this)

        if (!preferencesManager.isLoggedIn()) {
            startActivity(Intent(this, AuthActivity::class.java))
            finish()
        }

        logoutButton.setOnClickListener {
            val userRepository = UserRepositoryImpl(AuthService(), PreferencesManager(this))
            userRepository.logout()

            startActivity(Intent(this, AuthActivity::class.java))
            finish()
        }
    }
}