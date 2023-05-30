package com.example.easyselfhelp.ReadingPackage

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://www.googleapis.com/books/v1/"
private const val QUERY_STRING = "volumes?q=self_help"
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()
interface bookAPIService {
    @GET(QUERY_STRING)
    fun getBooks(): Call<GoogleBooksResponse>
}
object bookAPI{
    val book: bookAPIService by lazy {
        retrofit.create(bookAPIService::class.java)
    }
}