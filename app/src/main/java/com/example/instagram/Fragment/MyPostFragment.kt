package com.example.instagram.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.instagram.Adapter.MyPostRvAdapter
import com.example.instagram.BaseFragment
import com.example.instagram.Models.Post

import com.example.instagram.R
import com.example.instagram.databinding.FragmentMyPostBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.toObject
import com.google.firebase.ktx.Firebase

class MyPostFragment : BaseFragment<FragmentMyPostBinding> (FragmentMyPostBinding::inflate) {



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        myPost()


        }

    private fun myPost() {

        val postList = ArrayList<Post>()
        val adpter = MyPostRvAdapter(requireContext(), postList)
        binding.mainRv.layoutManager =
            StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        binding.mainRv.adapter = adpter

        Firebase.firestore.collection(Firebase.auth.currentUser!!.uid).get().addOnSuccessListener {

            val tempList = arrayListOf<Post>()

            for (i in it.documents) {
                //one post
                var post: Post = i.toObject<Post>()!!
                tempList.add(post)

            }
            // all post add
            postList.addAll(tempList)
            adpter.notifyDataSetChanged()

        }
    }
}
