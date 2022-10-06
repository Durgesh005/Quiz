package com.example.quiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.api_coroutine.ViewModel.UserViewModel
import com.example.quiz.Controller.RvSetup
import com.example.quiz.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth


class MainActivity : AppCompatActivity() {
    private var s1: Int? = null
    lateinit var binding: ActivityMainBinding
    var Data = arrayListOf<QuestionsItem>()
    var Option = arrayListOf<OptionsItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        userViewModel.list.observe(this, Observer {


            Log.e("TAG", "onCreate==========: ${it.size}")


            Data.addAll(it)



            QuestionRvsetup(Data)

            viewProfile()
            setProfileImage()
        })


    }

    fun QuestionRvsetup(Data: ArrayList<QuestionsItem>) {

        var adapter = RvSetup(this, Data)
        var layoutManager = LinearLayoutManager(this)
        binding.RvView.layoutManager = layoutManager
        binding.RvView.adapter = adapter
    }

    fun viewProfile() {

        binding.profileImage.setOnClickListener {

            var intent = Intent(this, UserActivity::class.java)
            startActivity(intent)

        }


    }

    private fun setProfileImage() {
        var firebaseAuth = FirebaseAuth.getInstance()
        var userImage = firebaseAuth.currentUser?.photoUrl

  /*      Glide.with(this)
            .load(userImage)
            .into(binding.profileImage);*/
    }
}