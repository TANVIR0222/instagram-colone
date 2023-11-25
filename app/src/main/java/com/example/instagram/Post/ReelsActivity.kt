package com.example.instagram.Post

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import com.example.instagram.HomeActivity
import com.example.instagram.Models.Post
import com.example.instagram.Models.Reel
import com.example.instagram.Models.Users
import com.example.instagram.R
import com.example.instagram.Utils.POST
import com.example.instagram.Utils.POST_FOLDER
import com.example.instagram.Utils.REEL
import com.example.instagram.Utils.REEl_FOLDER
import com.example.instagram.Utils.USER_NODE
import com.example.instagram.Utils.uploadImage
import com.example.instagram.Utils.uploadReel
import com.example.instagram.databinding.ActivityReelsBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.toObject
import com.google.firebase.ktx.Firebase

class ReelsActivity : AppCompatActivity() {



    val binding by lazy {


        ActivityReelsBinding.inflate(layoutInflater)
    }

// upload folder name firebase
    lateinit var vedioUrl:String
    lateinit var progressDialog: ProgressDialog
    private var launcher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        uri?.let {
            uploadReel(uri, REEl_FOLDER,progressDialog) { url ->
                vedioUrl=url
            }

        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        progressDialog= ProgressDialog(this)

        binding.materialCardView.setOnClickListener {
            launcher.launch("video/*")
        }

        binding.Rcancel.setOnClickListener {
            startActivity(Intent(this@ReelsActivity, HomeActivity::class.java))
            finish()
        }

        binding.RPost.setOnClickListener {

            Firebase.firestore.collection(USER_NODE).document(Firebase.auth.currentUser!!.uid).get()
                .addOnSuccessListener {
                var user:Users=it.toObject<Users>()!!

                    val reel: Reel = Reel(vedioUrl !!,binding.caption.editText?.text.toString(),user.image!!)
                    Firebase.firestore.collection(REEL).document().set(reel).addOnSuccessListener {
                        // reel add user information data base
                        Firebase.firestore.collection(Firebase.auth.currentUser!!.uid + REEL).document().set(reel).addOnSuccessListener {
                            startActivity(Intent(this@ReelsActivity,HomeActivity::class.java))
                            finish()
                        }
                    }
            }


        }

    }
}