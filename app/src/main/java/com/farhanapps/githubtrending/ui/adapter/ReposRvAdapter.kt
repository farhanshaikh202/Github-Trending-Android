package com.farhanapps.githubtrending.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.farhanapps.githubtrending.data.model.RepoModel
import com.farhanapps.githubtrending.databinding.RepoCardItemLayoutBinding
import com.farhanapps.githubtrending.ui.viewholder.RepoViewHolder

class ReposRvAdapter : RecyclerView.Adapter<RepoViewHolder>() {

    private val data = ArrayList<RepoModel>()

    fun setData(list: List<RepoModel>) {
        data.clear()
        data.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val binding =
            RepoCardItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RepoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        holder.bind(position, data[position], this)
    }

    override fun getItemCount(): Int {
        return data.size
    }
}