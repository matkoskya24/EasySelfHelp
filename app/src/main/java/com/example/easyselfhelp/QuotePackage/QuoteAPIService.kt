package com.example.easyselfhelp.QuotePackage

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import retrofit2.http.GET

private const val BASE_URL = "https://api.api-ninjas.com/v1/"
private const val QUERY_STRING = "quotes?category=inspirational"
private const val API_KEY = "9ymlA8PeyT7cb0luTTrr2A==MlfbL0zF2J77hDT9"
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(com.example.easyselfhelp.ReadingPackage.moshi))
    .baseUrl(com.example.easyselfhelp.ReadingPackage.BASE_URL)
    .build()
//interface quoteAPIService {
//    @GET(com.example.easyselfhelp.ReadingPackage.QUERY_STRING)
//   fun getQuotes(): Call<>
//}
//object QuoteAPI{
//    val quote: quoteAPIService by lazy {
//        retrofit.create(quoteAPIService::class.java)
//    }
//}

