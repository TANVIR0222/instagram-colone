package com.example.instagram.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.instagram.Models.Reel
import com.example.instagram.R
import com.example.instagram.databinding.ReelsDesingBinding
import com.squareup.picasso.Picasso

class ReelAdapter(var context: Context, var reelList: ArrayList<Reel>) :
    RecyclerView.Adapter<ReelAdapter.viewHolder>() {

    inner class viewHolder(var binding: ReelsDesingBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {

        var binding = ReelsDesingBinding.inflate(LayoutInflater.from(context), parent, false)
        return viewHolder(binding)
    }

    override fun getItemCount(): Int {
        return reelList.size
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {

        // image add reels profile
        Picasso.get().load(reelList.get(position).profileLink).placeholder(R.drawable.user).into(holder.binding.profile)
        // caption
        holder.binding.caption.setText(reelList.get(position).caption)
        // video
        holder.binding.videoView.setVideoPath(reelList.get(position).reelUrl)
        holder.binding.videoView.setOnPreparedListener {

            holder.binding.progressBar.visibility=View.GONE
            holder.binding.videoView.start()
        }
    }
}