package com.farhanapps.githubtrending.ui.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.farhanapps.githubtrending.databinding.AvatarItemBinding
import com.squareup.picasso.Picasso

class AvatarViewHolder(private val binding: AvatarItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(position: Int, url: String) {
        Picasso.get().load(url).into(binding.avatarImg)
    }
}