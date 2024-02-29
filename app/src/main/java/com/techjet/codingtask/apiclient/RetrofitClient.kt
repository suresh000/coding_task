package com.techjet.codingtask.apiclient

import com.google.gson.GsonBuilder
import com.techjet.codingtask.AppController
import com.techjet.codingtask.BuildConfig
import com.techjet.codingtask.utils.NetworkUtil
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {

    private const val BASE_URL = BuildConfig.BASE_URL

    private val mRetrofit: Retrofit

    init {
        val gson = GsonBuilder()
            .setLenient()
            .serializeNulls()
            .create()

        mRetrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient().build())
            .build()
    }

    fun getRetrofit(): Retrofit {
        return mRetrofit
    }

    @Synchronized
    fun getClient() : ApiService {
        return mRetrofit.create(ApiService::class.java)
    }

    private fun httpClient(): OkHttpClient.Builder {
        val httpClient = OkHttpClient.Builder()

        val logging = HttpLoggingInterceptor()
        if (BuildConfig.IS_PROD) {
            logging.setLevel(HttpLoggingInterceptor.Level.NONE)
        } else {
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        }
        // add logging as last interceptor
        httpClient.addInterceptor(logging)

        httpClient.callTimeout(60, TimeUnit.SECONDS)
        httpClient.connectTimeout(60, TimeUnit.SECONDS)
        httpClient.readTimeout(60, TimeUnit.SECONDS)
        httpClient.writeTimeout(60, TimeUnit.SECONDS)

        val cacheSize = (10 * 1024 * 1024).toLong()
        val myCache = Cache(AppController.instance.cacheDir, cacheSize)
        httpClient.cache(myCache)

        httpClient.addInterceptor(Interceptor { chain: Interceptor.Chain ->
            var original = chain.request()

            original = if (NetworkUtil.isConnected(AppController.instance)) {
                original.newBuilder()
                    .header("Cache-Control", "public, max-age=" + 5)
                    .header("Accept", "application/json")
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .build()
            } else {
                original.newBuilder()
                    .header("Cache-Control", "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7)
                    .header("Accept", "application/json")
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .build()
            }

            chain.proceed(original)
        })

        return httpClient
    }

    interface Listener<T, U> {
        fun onResponse(response: T)
        fun onError(error: U)
    }

}