package com.frakton.moviesapp.retrofit

import com.frakton.moviesapp.utils.Constants
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitHelper {
    private const val baseUrl = "https://developers.themoviedb.org/"

    private var moviesApi: MoviesApiService? = null

    private fun getInstance(): Retrofit {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        val okHttpClient = OkHttpClient.Builder().addInterceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("Authorization", "${Constants.MOVIES_API_KEY}")
                .addHeader("Content-Type", "application/json")
                .build()
            chain.proceed(request)
        }
        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(okHttpClient.build())
            .build()
    }

    fun getMoviesApiService(): MoviesApiService? {
        if (moviesApi == null) {
            moviesApi = getInstance().create(MoviesApiService::class.java)
        }
        return moviesApi
    }
}