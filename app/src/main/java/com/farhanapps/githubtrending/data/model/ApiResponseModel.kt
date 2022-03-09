package com.farhanapps.githubtrending.data.model

data class ApiResponseModel<Type>(
    val msg: String,
    val items: Type?
)