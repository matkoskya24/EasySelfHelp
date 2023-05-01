package com.example.easyselfhelp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.findNavController
import com.example.easyselfhelp.databinding.FragmentAddBudgetItemBinding


class AddBudgetItemFragment : Fragment() {
    private var _binding: FragmentAddBudgetItemBinding? = null
    val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddBudgetItemBinding.inflate(inflater, container, false)
        val rootView = binding.root
        binding.submitBudgetItemButton.setOnClickListener {
            val name = binding.itemNameEditText.text.toString()
            val category = binding.budgetCategoryEditText.text.toString()
            val amount = binding.budgetAmountEditText.text.toString().toDouble()
            setFragmentResult("requestKey", bundleOf("bundleKey" to name))
            rootView.findNavController().navigateUp()
        }
        return rootView
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}