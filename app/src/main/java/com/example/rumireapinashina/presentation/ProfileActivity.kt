package com.example.rumireapinashina.presentation

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.example.rumireapinashina.R
import com.example.rumireapinashina.presentation.ProfileFragment
import com.google.firebase.auth.FirebaseAuth
import com.example.rumireapinashina.BaseActivity

class ProfileActivity : BaseActivity() {

    private lateinit var logoutButton: Button
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        setupNavigationDrawer()

        auth = FirebaseAuth.getInstance()

        // Инициализация фрагмента
        if (savedInstanceState == null) {
            replaceFragment(ProfileFragment())
        }

        logoutButton = findViewById(R.id.logoutButton)
        logoutButton.setOnClickListener {
            logout()
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

    private fun logout() {
        auth.signOut()
        val intent = Intent(this, AuthActivity::class.java)
        startActivity(intent)
        finish()
    }
}
