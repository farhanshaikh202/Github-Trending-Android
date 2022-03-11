package com.farhanapps.githubtrending.ui.viewholder

import android.graphics.Color
import android.text.Html
import androidx.core.content.ContextCompat
import androidx.core.view.isGone
import androidx.recyclerview.widget.RecyclerView
import com.farhanapps.githubtrending.R
import com.farhanapps.githubtrending.data.model.RepoModel
import com.farhanapps.githubtrending.databinding.RepoCardItemLayoutBinding
import com.farhanapps.githubtrending.ui.adapter.AvatarRvAdapter
import com.farhanapps.githubtrending.ui.viewmodel.ReposViewModel

class RepoViewHolder(private val binding: RepoCardItemLayoutBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(position: Int, repo: RepoModel, viewModel: ReposViewModel) {
        val context = binding.root.context
        val isSelected = viewModel.isSelected(repo)
        binding.repoItemTitleText.text = Html.fromHtml(repo.repo.replace("/", "/<b>") + "</b>")
        binding.repoItemDesc.text = repo.desc
        binding.repoItemLangText.text = repo.lang
        binding.repoItemStarsAllText.text = repo.stars
        binding.repoItemStarsTodayText.text = repo.added_stars
        binding.repoItemForksText.text = repo.forks

        binding.repoItemBuiltByRv.adapter = AvatarRvAdapter(repo.avatars)

        binding.repoItemIconImg.setImageResource(if (isSelected) R.drawable.ic_baseline_check_circle_24 else R.drawable.ic_repo_icon)
        binding.repoItemDesc.isGone = repo.desc.isEmpty()
        binding.repoItemLangText.isGone = repo.lang.isEmpty()
        binding.repoItemStarsAllText.isGone = repo.stars.isEmpty()
        binding.repoItemStarsTodayText.isGone = repo.added_stars.isEmpty()
        binding.repoItemForksText.isGone = repo.forks.isEmpty()

        binding.root.setBackgroundColor(
            if (isSelected) ContextCompat.getColor(
                context,
                R.color.blue_alpha
            ) else Color.WHITE
        )

    }
}