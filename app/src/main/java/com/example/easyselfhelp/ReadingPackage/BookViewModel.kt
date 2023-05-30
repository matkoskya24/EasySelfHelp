package com.example.easyselfhelp.ReadingPackage

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BookViewModel : ViewModel() {
    private var _response = MutableLiveData<List<Book>>()
    val response: LiveData<List<Book>> get() = _response
    fun getBooks(){
        val request = bookAPI.book.getBooks()
        request.enqueue(object: Callback<GoogleBooksResponse> {
            override fun onResponse(call: Call<GoogleBooksResponse>, response: Response<GoogleBooksResponse>) {
                var listOfBooksFetched = mutableListOf<Book>()

                var googleBooksResponse: GoogleBooksResponse? = response.body()
                val bookFeaturesList: List<BookFeatures> = googleBooksResponse?.bookFeaturesList?: mutableListOf()

                for(bookFeatures in bookFeaturesList){
                    val bookProperties = bookFeatures.bookProperties

                    val title = bookProperties.title?:"unknown title"
                    val subtitle = bookProperties.subtitle?:""
                    val authors = bookProperties.authors?: listOf("Unknown Author")
                    val url = bookProperties.url
                    val imageuri = bookProperties.imageProperties.uri

                    val newBook = Book(title, subtitle, authors, url, imageuri)
                    listOfBooksFetched.add(newBook)
                }
                _response.value = listOfBooksFetched
            }
            override fun onFailure(call: Call<GoogleBooksResponse>, t: Throwable) {
                Log.d("RESPONSE", "Failure: " + t.message)
            }
        })
    }
}