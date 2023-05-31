package com.example.easyselfhelp.ReadingPackage

import android.content.Intent
import android.net.Uri
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.easyselfhelp.databinding.BookItemLayoutBinding
class BookViewHolder(val binding: BookItemLayoutBinding):RecyclerView.ViewHolder(binding.root) {
    private lateinit var currentBook: Book
    init {
        binding.root.setOnClickListener {
            val bookUri = Uri.parse(currentBook.url)
            val websiteIntent = Intent(Intent.ACTION_VIEW, bookUri)
            itemView.context.startActivity(websiteIntent)
        }
    }
    fun bindBook(book: Book){
        currentBook = book
        val title = book.title
        val uri = book.imageuri.toUri().buildUpon().scheme("https").build()
        var author: String = ""
        var counter = 0
        while(counter < currentBook.authors.size){
            author = author + currentBook.authors[counter]
            if(counter < (currentBook.authors.size - 1)){
                author = author + ", "
            }
            counter++
        }
        Glide.with(itemView).load(uri).into(binding.bookImageView)
        binding.bookTitleTextView.text = title
        binding.bookAuthorTextView.text = author
    }
}