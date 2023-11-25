package com.example.instagram

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.instagram.Adapter.ReelAdapter
import com.example.instagram.Adapter.ViewPageAdapter
import com.google.firebase.auth.FirebaseAuth

open class BaseFragment<vB : ViewBinding>(
    private val bindingInflater: (inflater: LayoutInflater) -> vB


) : Fragment() {

    private var _binding: vB? = null

    val binding: vB
        get() = _binding as vB

    lateinit var firebaseAuth: FirebaseAuth

    lateinit var viewPageAdapter: ViewPageAdapter
    lateinit var adapter: ReelAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = bindingInflater.invoke(inflater)
        firebaseAuth=FirebaseAuth.getInstance()

        return binding.root


    }


}
