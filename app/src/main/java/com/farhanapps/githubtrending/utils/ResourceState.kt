package com.farhanapps.githubtrending.utils

enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}

data class ResourceState<out T>(val status: Status, val data: T?, val message: String?) {

    companion object {

        fun <T> success(data: T?): ResourceState<T> {
            return ResourceState(Status.SUCCESS, data, null)
        }

        fun <T> error(msg: String, data: T?): ResourceState<T> {
            return ResourceState(Status.ERROR, data, msg)
        }

        fun <T> loading(data: T?): ResourceState<T> {
            return ResourceState(Status.LOADING, data, null)
        }

    }

}