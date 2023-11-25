package com.example.instagram.Post

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.instagram.HomeActivity
import com.example.instagram.Models.Post
import com.example.instagram.Models.Users
import com.example.instagram.Utils.POST
import com.example.instagram.Utils.POST_FOLDER
import com.example.instagram.Utils.uploadImage
import com.example.instagram.databinding.ActivityAddPostBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class AddPostActivity : AppCompatActivity() {


    val binding by lazy {
        ActivityAddPostBinding.inflate(layoutInflater)

    }
    //
    lateinit var user: Users
    var imageUrl: String? = null

    private var launcher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        uri?.let {
            uploadImage(uri, POST_FOLDER) { url ->
                binding.uploadImage.setImageURI(uri)
                imageUrl=url
            }

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setSupportActionBar(binding.materialToolbar)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
        getSupportActionBar()?.setDisplayShowHomeEnabled(true)
        binding.materialToolbar.setNavigationOnClickListener {
            startActivity(Intent(this@AddPostActivity,HomeActivity::class.java))
            finish()
        }

        binding.uploadImage.setOnClickListener {
            launcher.launch("image/*")
        }

        binding.cancel.setOnClickListener {
            startActivity(Intent(this@AddPostActivity,HomeActivity::class.java))
            finish()
        }

        // post add
        binding.addPost.setOnClickListener {

            val post: Post = Post(imageUrl !!,binding.caption.editText?.text.toString())
            Firebase.firestore.collection(POST).document().set(post).addOnSuccessListener {
                // post add user information data base
                Firebase.firestore.collection(Firebase.auth.currentUser!!.uid).document().set(post).addOnSuccessListener {
                   startActivity(Intent(this@AddPostActivity,HomeActivity::class.java))
                    finish()
                }
            }

        }

    }
}