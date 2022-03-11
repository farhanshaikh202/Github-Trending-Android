package com.farhanapps.githubtrending.utils.interfaces

interface OnItemClickListener<T> {
    fun onItemClick(position: Int, item: T)
}
