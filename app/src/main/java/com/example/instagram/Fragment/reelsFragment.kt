package com.example.instagram.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.instagram.Adapter.MyReeladapter
import com.example.instagram.Adapter.ReelAdapter
import com.example.instagram.BaseFragment
import com.example.instagram.Models.Reel
import com.example.instagram.R
import com.example.instagram.Utils.REEL
import com.example.instagram.databinding.FragmentReelsBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.toObject
import com.google.firebase.ktx.Firebase

class reelsFragment : BaseFragment<FragmentReelsBinding> (FragmentReelsBinding::inflate){


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        reelSee()
    }

    private fun reelSee() {

        val reelList = ArrayList<Reel>()
        val adapter = ReelAdapter(requireContext(), reelList )

        binding.videoView.adapter=adapter



        Firebase.firestore.collection(REEL).get()
            .addOnSuccessListener {

            val tempList = arrayListOf<Reel>()
            // one reel read
                reelList.clear()

                for (i in it.documents) {
                //one post
                val reel = i.toObject<Reel>()!!
                tempList. add(reel)

            }
            // all post add
            reelList.addAll(tempList)
                reelList.reverse()
            adapter.notifyDataSetChanged()

        }


    }


}