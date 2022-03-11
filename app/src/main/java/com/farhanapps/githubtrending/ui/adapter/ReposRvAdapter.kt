package com.farhanapps.githubtrending.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.farhanapps.githubtrending.data.model.RepoModel
import com.farhanapps.githubtrending.databinding.RepoCardItemLayoutBinding
import com.farhanapps.githubtrending.ui.viewholder.RepoViewHolder
import com.farhanapps.githubtrending.ui.viewmodel.ReposViewModel
import com.farhanapps.githubtrending.utils.interfaces.OnItemClickListener

class ReposRvAdapter : RecyclerView.Adapter<RepoViewHolder>() {

    private var listener: OnItemClickListener<RepoModel>? = null
    private val data = ArrayList<RepoModel>()
    private lateinit var viewModel: ReposViewModel

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
        holder.bind(position, data[position], viewModel)
        holder.itemView.setOnClickListener {
            listener?.onItemClick(position, data[position])
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun setOnItemClickListener(listener: OnItemClickListener<RepoModel>) {
        this.listener = listener
    }

    fun setViewModel(viewModel: ReposViewModel) {
        this.viewModel = viewModel
    }
}