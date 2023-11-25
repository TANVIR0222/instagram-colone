package com.example.instagram

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.instagram.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.statusBarColor=Color.TRANSPARENT

        Handler(Looper.getMainLooper()).postDelayed({

            if(FirebaseAuth.getInstance().currentUser == null)
            startActivity(Intent(this, LoginActivity::class.java))

            else
                startActivity(Intent(this, HomeActivity::class.java))
            finish()

        }, 1000)


    }
}