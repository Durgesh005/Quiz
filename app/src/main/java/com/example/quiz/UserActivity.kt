package com.example.quiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.quiz.databinding.ActivityUserBinding
import com.google.firebase.auth.FirebaseAuth

class UserActivity : AppCompatActivity() {
    lateinit var binding: ActivityUserBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUserInfo()
        backtoHome()

    }

    fun setUserInfo() {

        var firebaseAuth = FirebaseAuth.getInstance()

        var user = firebaseAuth.currentUser

        Glide.with(this)
            .load(user?.photoUrl)
            .into(binding.profileImage);

        binding.userNameTxt.setText(user?.displayName)

        binding.userEmailTxt.setText(user?.email)

        binding.userNumberTxt.setText(user?.phoneNumber)

        binding.Logout.setOnClickListener {
            Logout()
        }


    }

    fun backtoHome() {

        binding.back.setOnClickListener {

            onBackPressed()

        }

    }

    private fun Logout() {
        var firebaseAuth = FirebaseAuth.getInstance()
        firebaseAuth.signOut()
        var intent = Intent(this, UserLoginActivity::class.java)
        startActivity(intent)
    }
}