package com.example.instagram

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.instagram.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivityLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)



        binding.logIn.setOnClickListener {

            val email = binding.email.editText?.text.toString().trim()
            val password = binding.password.editText?.text.toString().trim()

            userLogIn(email,password)
        }

    }

    private fun userLogIn(email: String, password: String) {

        firebaseAuth.signInWithEmailAndPassword(email,password).addOnSuccessListener {
            startActivity(Intent(this, HomeActivity::class.java))

        }.addOnFailureListener {
            Toast.makeText(this,"fail", Toast.LENGTH_SHORT).show()


        }


    }
}