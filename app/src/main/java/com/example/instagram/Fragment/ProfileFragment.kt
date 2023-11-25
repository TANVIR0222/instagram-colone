package com.example.instagram.Fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.instagram.Adapter.ViewPageAdapter
import com.example.instagram.BaseFragment
import com.example.instagram.Models.Users

import com.example.instagram.R
import com.example.instagram.Utils.USER_NODE
import com.example.instagram.databinding.FragmentProfileBinding
import com.example.instagram.singUpActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.toObject
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso

class ProfileFragment : BaseFragment<FragmentProfileBinding>(FragmentProfileBinding::inflate){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)




    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewPageAdapter= ViewPageAdapter(requireActivity().supportFragmentManager)
        viewPageAdapter.addFragment(MyPostFragment(),"My Post")
        viewPageAdapter.addFragment(MyReelsFragment(),"My Reels")

        binding.viewPage.adapter=viewPageAdapter
        binding.tabLayout.setupWithViewPager(binding.viewPage)




    }


    // user profile
    override fun onStart() {
        super.onStart()

        Firebase.firestore.collection(USER_NODE).document(Firebase.auth.currentUser!!.uid).get().addOnSuccessListener {
            val user: Users = it.toObject<Users>()!!
            binding.name.text=user.name
            binding.bio.text=user.email

            if (!user.image.isNullOrEmpty()){
                Picasso.get().load(user.image).into(binding.profile)

            }

        }



    }

}