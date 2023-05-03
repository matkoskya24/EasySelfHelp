package com.example.easyselfhelp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.easyselfhelp.databinding.FragmentBudgetItemDeleteBinding


class BudgetItemDeleteFragment : Fragment() {
    private var _binding: FragmentBudgetItemDeleteBinding? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBudgetItemDeleteBinding.inflate(inflater, container, false)
        val rootView = binding.root
        return rootView
    }

}