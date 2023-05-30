package com.example.easyselfhelp.ReadingPackage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.easyselfhelp.databinding.BookItemLayoutBinding

class BookAdapter(val BookList: List<Book>): RecyclerView.Adapter<BookViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = BookItemLayoutBinding.inflate(layoutInflater, parent, false)
        return BookViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = BookList[position]
        holder.bindBook(book)
    }

    override fun getItemCount(): Int {
        return BookList.size
    }
}