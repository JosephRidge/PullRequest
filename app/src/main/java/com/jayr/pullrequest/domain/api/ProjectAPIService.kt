package com.jayr.pullrequest.domain.api

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


object ProjectAPIService {
//    - baseURL
    private const val  BASE_URL = "https://24pullrequests.com/"

//    - instance of the logger
    private val logger = HttpLoggingInterceptor()
        .setLevel(Level.BASIC)

    // client
    var client: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(logger)
        .build()

//    - converter => instance of the moshi convertor
    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()


//    - instance of the client
var projectAPIInterface: ProjectAPIService by lazy{
    Retrofit.Builder().baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .client(client)
        .build()
        .create(ProjectAPIService::class.java)
}
}