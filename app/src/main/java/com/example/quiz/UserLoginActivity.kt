package com.example.quiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.widget.CompoundButton
import android.widget.Toast
import com.example.quiz.databinding.ActivityRegisterPageBinding
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.GoogleApiClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class UserLoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityRegisterPageBinding
    private val RC_SIGN_IN = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.LoginBtn.setOnClickListener {
            Login(binding.EmailId.text.toString(), binding.Password.text.toString())

        }


        binding.text4.setOnClickListener {
            Toast.makeText(
                this@UserLoginActivity,
                "Please Use The Google Login At That Time",
                Toast.LENGTH_SHORT
            ).show()
        }

        hidePassword()
        loginwithgoogle()

    }



    private fun hidePassword() {

        binding.PasswordShow.setOnCheckedChangeListener(object :
            CompoundButton.OnCheckedChangeListener {
            override fun onCheckedChanged(button: CompoundButton?, boolean: Boolean) {
                if (boolean) {

                    binding.Password.transformationMethod =
                        HideReturnsTransformationMethod.getInstance()

                } else {

                    binding.Password.transformationMethod =
                        PasswordTransformationMethod.getInstance()

                }
            }
        })

    }

    private fun Login(Email: String, Password: String) {
        var firebaseAuth = FirebaseAuth.getInstance()
        firebaseAuth.signInWithEmailAndPassword(Email, Password)
            .addOnSuccessListener { res ->
                var intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }.addOnFailureListener { error ->
                Log.e("TAG", "Login: $error")
            }
    }


    //Login with Google
    fun loginwithgoogle() {

        binding.GoogleBtn.setOnClickListener {


            var signInRequest = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.web_client_id)).requestEmail()
                .build()


            var googleApiClient =
                GoogleApiClient.Builder(this).addApi(Auth.GOOGLE_SIGN_IN_API, signInRequest).build()

            var intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient)

            startActivityForResult(intent, RC_SIGN_IN)


        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {

            RC_SIGN_IN -> {


                var result = Auth.GoogleSignInApi.getSignInResultFromIntent(data!!)

                var account = result?.signInAccount

                googleCredential(account?.idToken.toString())


            }

        }

    }

    private fun googleCredential(idtoken: String) {

        var credential = GoogleAuthProvider.getCredential(idtoken, null)

        var firebaseAuth = FirebaseAuth.getInstance()
        firebaseAuth.signInWithCredential(credential).addOnSuccessListener { result ->

            Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show()

            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
            .addOnFailureListener { error ->

                Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show()
            }

    }


}