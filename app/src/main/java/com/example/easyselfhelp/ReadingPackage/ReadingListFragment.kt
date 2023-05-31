package com.example.easyselfhelp.ReadingPackage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.easyselfhelp.databinding.FragmentReadingListBinding
import androidx.lifecycle.Observer


class ReadingListFragment : Fragment() {
    private var _binding: FragmentReadingListBinding? = null
    val binding get() = _binding!!
    private val viewModel: BookViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentReadingListBinding.inflate(inflater, container, false)
        val rootview = binding.root
        viewModel.getBooks()
        viewModel.response.observe(viewLifecycleOwner, Observer { booklist ->
            val adapter = BookAdapter(booklist)
            binding.bookRecyclerView.adapter = adapter
        })
        return rootview
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}