package com.farhanapps.githubtrending.utils.extensions

import com.google.gson.Gson

inline fun <reified T> T.toJSON(): String {
    return Gson().toJson(this)
}

inline fun <reified T> String.toModel(): T {
    return Gson().fromJson(this, T::class.java)
}