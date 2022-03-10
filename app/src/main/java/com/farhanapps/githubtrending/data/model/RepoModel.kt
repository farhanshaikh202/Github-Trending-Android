package com.farhanapps.githubtrending.data.model

data class RepoModel(
    val repo: String,
    val repo_link: String,
    val desc: String,
    val lang: String,
    val stars: String,
    val forks: String,
    val added_stars: String,
    val avatars: ArrayList<String>,
    var isSelected: Boolean = false
)