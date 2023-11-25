package com.example.instagram.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.instagram.Adapter.MyPostRvAdapter
import com.example.instagram.Adapter.MyReeladapter
import com.example.instagram.BaseFragment
import com.example.instagram.Models.Post
import com.example.instagram.Models.Reel

import com.example.instagram.R
import com.example.instagram.Utils.REEL
import com.example.instagram.databinding.ActivityReelsBinding
import com.example.instagram.databinding.FragmentMyReelsBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.toObject
import com.google.firebase.ktx.Firebase


class MyReelsFragment :BaseFragment<FragmentMyReelsBinding> (FragmentMyReelsBinding::inflate){

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        myReels()



    }

    private fun myReels() {
        val reelList = ArrayList<Reel>()
        val adpter = MyReeladapter(requireContext(), reelList )
        binding.mainReelsRCV.layoutManager =
            StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        binding.mainReelsRCV.adapter = adpter

        Firebase.firestore.collection(Firebase.auth.currentUser!!.uid + REEL).get().addOnSuccessListener {

            val tempList = arrayListOf<Reel>()

            // one reel read

            for (i in it.documents) {
                //one post
                val reel: Reel = i.toObject<Reel>()!!
                tempList.add(reel)

            }
            // all post add
            reelList.addAll(tempList)
            adpter.notifyDataSetChanged()


        }
    }
}

