package com.toshiro.hdwallper.apiHelper

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {
    val BASE_URL = "http://tapetee.com/"
    var retrofit: Retrofit? = null

    val getClient: Retrofit
        get() {
            val httpClient = OkHttpClient.Builder().connectTimeout(2000, TimeUnit.SECONDS)
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            httpClient.addInterceptor { chain ->
                val original = chain.request()
                val request = original.newBuilder()
                        .header("Accept", "application/json")
                        .header("Content-Type", "application/json")
                        .method(original.method(), original.body())
                        .build()
                chain.proceed(request)
            }
            httpClient.addInterceptor(interceptor)
            val client = httpClient.build()

            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .client(client)
                        .build()
            }
            return retrofit!!
        }
}