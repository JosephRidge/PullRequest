package com.jayr.pullrequest.domain.api

import com.jayr.pullrequest.domain.`interface`.PRApiInterface
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitClient {
    /*
    * - defining the base url
    * - logging intercepror
    * - define the okhttp client (interfceptor)
    * - moshi converter
    * - retrofit client themselves
    * */

    private const val BASE_URL = "https://24pullrequests.com/api"

    val logging: HttpLoggingInterceptor = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BASIC)

    val client: OkHttpClient = OkHttpClient
        .Builder()
        .addInterceptor(logging)
        .build()

    val moshi:Moshi= Moshi
        .Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    val prApiInterface: PRApiInterface by lazy {
        Retrofit.Builder()
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(PRApiInterface::class.java)
    }



}























