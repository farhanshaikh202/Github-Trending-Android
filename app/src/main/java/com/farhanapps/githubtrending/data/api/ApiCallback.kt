package com.farhanapps.githubtrending.data.api

import android.view.View
import com.farhanapps.githubtrending.utils.AppUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

interface ApiCallback<Any> : Callback<Any> {
    override fun onResponse(call: Call<Any>, response: Response<Any>) {
        if (response.isSuccessful) {
            onResponse(response.body())
        } else {
            getView()?.let {
                AppUtils.handleError(it, response)
            }
            onFail(AppUtils.getErrorBody(response))
        }
    }

    override fun onFailure(call: Call<Any>, t: Throwable) {
        onFail(t.localizedMessage)
        t.printStackTrace()
        getView()?.let {
            AppUtils.showSnackBar(it, t.localizedMessage!!)
        }
    }

    fun onResponse(data: Any?)

    fun onFail(message: String?)

    fun getView(): View?

}