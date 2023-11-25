package com.example.instagram.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.instagram.Models.Post
import com.example.instagram.Models.Reel
import com.example.instagram.databinding.ItemPostRcvBinding
import com.squareup.picasso.Picasso

class MyReeladapter(var context: Context, var reelList: ArrayList<Reel>) :
    RecyclerView.Adapter<MyReeladapter.ViewHolder>() {


    inner class ViewHolder(var binding: ItemPostRcvBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        var binding= ItemPostRcvBinding.inflate(LayoutInflater.from(context),parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return reelList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        Glide.with(context)

            .load(reelList.get(position).reelUrl)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(holder.binding.RcvPost)
//        Picasso.get().load(postList.get(position).postUrl).into(holder.binding.RcvPost)
    }
}