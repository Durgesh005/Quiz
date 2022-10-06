package com.example.quiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.quiz.databinding.ActivitySpalashScreenBinding
import com.google.firebase.auth.FirebaseAuth

class SplashScreen : AppCompatActivity() {
    lateinit var binding: ActivitySpalashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySpalashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var firebaseAuth = FirebaseAuth.getInstance()
        var user = firebaseAuth.currentUser

        Handler().postDelayed({
            if (user != null) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            } else {
                val intent = Intent(this, UserLoginActivity::class.java)
                startActivity(intent)
            }
            finish()
        }, 2500)
    }
}