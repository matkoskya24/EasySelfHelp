package com.example.easyselfhelp.QuotePackage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.easyselfhelp.R
import com.example.easyselfhelp.databinding.FragmentQuotesBinding

class QuotesFragment : Fragment() {
    private var _binding: FragmentQuotesBinding? = null
    val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       _binding = FragmentQuotesBinding.inflate(inflater, container, false)
        val rootView = binding.root
        return rootView
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}