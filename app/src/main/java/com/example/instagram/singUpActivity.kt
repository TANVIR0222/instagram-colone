package com.example.instagram

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import com.example.instagram.Models.Users
import com.example.instagram.Utils.USER_NODE
import com.example.instagram.Utils.USER_PROFILE_FOLDER
import com.example.instagram.Utils.uploadImage
import com.example.instagram.databinding.ActivitySingUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.toObject
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso

class singUpActivity : AppCompatActivity() {
    lateinit var binding: ActivitySingUpBinding
    lateinit var firebaseAuth: FirebaseAuth
    lateinit var user: Users

    // profile image upload

    private var launcher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        uri?.let {
            uploadImage(uri, USER_PROFILE_FOLDER) {
                if (it != null) {
                    user.image = it
                    binding.profile.setImageURI(uri)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        firebaseAuth = FirebaseAuth.getInstance()
        user = Users()

        super.onCreate(savedInstanceState)
        binding = ActivitySingUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // edite profile
//        if(intent.hasExtra("mode")){
//            if (intent.getIntExtra("mode", -1) == 1){
//                binding.singUp.text="Update profile"
//
//                Firebase.firestore.collection(USER_NODE).document(Firebase.auth.currentUser!!.uid).get()
//                    .addOnSuccessListener {
//                        user  = it.toObject<Users>()!!
//
//                        if (!user.image.isNullOrEmpty()){
//                            Picasso.get().load(user.image).into(binding.profile)
//
//                        }
//
//
//                }
//            }
//        }

        binding.singUp.setOnClickListener {


                val name = binding.name.editText?.text.toString().trim()
                val email = binding.email.editText?.text.toString().trim()
                val password = binding.password.editText?.text.toString().trim()

                userRegistration(name, email, password)
            }


        binding.plus.setOnClickListener {
            launcher.launch("image/*")
        }

    }

    private fun userRegistration(name: String, email: String, password: String) {

        firebaseAuth.createUserWithEmailAndPassword(email, password)

            .addOnSuccessListener {

                user.name=name
                user.email=email
                user.password=password
                Firebase.firestore.collection(USER_NODE).document(Firebase.auth.currentUser!!.uid).set(user).addOnSuccessListener{
                    Toast.makeText(this,"success",Toast.LENGTH_SHORT).show()

                }


            }.addOnFailureListener {
                startActivity(Intent(this,LoginActivity::class.java))

            }
        }

    }
