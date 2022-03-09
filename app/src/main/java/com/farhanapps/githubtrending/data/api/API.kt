package com.farhanapps.githubtrending.data.api

import com.farhanapps.githubtrending.data.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class API {
    companion object {

        private var service: ApiService? = null

        fun get(): ApiService {
            if (service == null) {

                val client = OkHttpClient.Builder()
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS)

                val logging = HttpLoggingInterceptor()
                logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                client.addInterceptor(logging)

                val retrofit = Retrofit.Builder()
                    .baseUrl(Constants.HOST_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client.build())
                    .build()

                service = retrofit.create(ApiService::class.java)
            }

            return service!!
        }
    }
}