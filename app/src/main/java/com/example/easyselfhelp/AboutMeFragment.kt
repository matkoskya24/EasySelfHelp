package com.example.easyselfhelp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.easyselfhelp.databinding.FragmentAboutMeBinding

class AboutMeFragment : Fragment() {
    private var _binding: FragmentAboutMeBinding? = null
    val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAboutMeBinding.inflate(inflater, container, false)
        val rootview = binding.root
        binding.goBackDevFragment.setOnClickListener {
            rootview.findNavController().navigateUp()
        }
        return rootview
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}