package com.example.instagram.Fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.instagram.Post.AddPostActivity
import com.example.instagram.Post.ReelsActivity
import com.example.instagram.R
import com.example.instagram.databinding.FragmentAddBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class addFragment : BottomSheetDialogFragment() {
    lateinit var binding: FragmentAddBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =FragmentAddBinding.inflate(inflater,container,false)

        binding.post.setOnClickListener {
          activity?.startActivity(Intent(requireContext(),AddPostActivity::class.java))
        }
        binding.reel.setOnClickListener {
            activity?.startActivity(Intent(requireContext(),ReelsActivity::class.java))
        }


        return binding.root
    }

}