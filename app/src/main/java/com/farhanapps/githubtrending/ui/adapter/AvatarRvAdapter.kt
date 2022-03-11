package com.farhanapps.githubtrending.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.farhanapps.githubtrending.databinding.AvatarItemBinding
import com.farhanapps.githubtrending.ui.viewholder.AvatarViewHolder

class AvatarRvAdapter(private val data: ArrayList<String> = ArrayList()) :
    RecyclerView.Adapter<AvatarViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AvatarViewHolder {
        return AvatarViewHolder(
            AvatarItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: AvatarViewHolder, position: Int) {
        holder.bind(position, data[position])
    }

    override fun getItemCount(): Int {
//        return if (data.size > 5) 6 else data.size
        return data.size
    }
}