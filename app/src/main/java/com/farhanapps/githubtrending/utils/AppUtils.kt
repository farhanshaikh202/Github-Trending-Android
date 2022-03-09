package com.farhanapps.githubtrending.utils

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.view.View
import androidx.core.app.ActivityCompat
import com.farhanapps.githubtrending.BuildConfig
import com.farhanapps.githubtrending.data.model.ApiResponseModel
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import retrofit2.Response

object AppUtils {
    fun getAppVersion(): String {
        return BuildConfig.VERSION_NAME + " (" + BuildConfig.VERSION_CODE + ")"
    }

    fun getDeviceName(): String {
        val model = Build.MODEL
        val manufacture = Build.MANUFACTURER
        return if (model.startsWith(manufacture)) {
            model
        } else {
            "$manufacture $model"
        }
    }

    fun isOnline(context: Context): Boolean {
        val online = NetworkUtils.isConnected(context)
        if (!online && context is Activity)
            showSnackBar(context, "No internet connection")
        return online
    }

    fun showSnackBar(activity: Activity, msg: String) {
        Snackbar.make(activity.findViewById(android.R.id.content), msg, Snackbar.LENGTH_LONG)
            .show()
    }

    fun showSnackBar(view: View, msg: String) {
        Snackbar.make(view, msg, Snackbar.LENGTH_LONG)
            .show()
    }

    fun getErrorBody(response: Response<*>): String {
        val body: ApiResponseModel<*>? = parseError(response)
        return body?.msg ?: response.message()
    }

    fun handleError(view: View, response: Response<*>) {
        val body: ApiResponseModel<*>? = parseError(response)
        if (body != null) {
            val snackbar: Snackbar =
                Snackbar.make(view, body.msg, Snackbar.LENGTH_INDEFINITE)
                    .setAction(
                        "OK",
                        { v: View? -> }
                    )
            snackbar.show()
        } else Snackbar.make(view, response.message(), Snackbar.LENGTH_LONG).show()
    }

    fun parseError(response: Response<*>): ApiResponseModel<*>? {
        val error: ApiResponseModel<*>
        val gson = Gson()
        try {
            error = gson.fromJson(response.errorBody()!!.string(), ApiResponseModel::class.java)
        } catch (e: Exception) {
            return null
        }
        return error
    }

    fun hasPermissions(context: Context?, vararg permissions: String): Boolean {
        if (context != null) {
            for (permission in permissions) {
                if (ActivityCompat.checkSelfPermission(
                        context,
                        permission
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    return false
                }
            }
        }
        return true
    }

}