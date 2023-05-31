package com.example.easyselfhelp.BudgetItemPackage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
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
            val amountString = binding.budgetAmountEditText.text.toString()
            if(name != "" && amountString != "") {
                val amount = amountString.toDouble()
                val resultBundle: Bundle = bundleOf()
                resultBundle.putString("budgetItemName", name)
                resultBundle.putString("budgetItemCategory", category)
                resultBundle.putDouble("budgetItemAmount", amount)
                setFragmentResult("requestKey", bundleOf("bundleKey" to resultBundle))
                rootView.findNavController().navigateUp()
            }else if(name == ""){
                Toast.makeText(activity, "Enter Budget Item Name", Toast.LENGTH_LONG).show()
            }else if(amountString == ""){
                Toast.makeText(activity, "Enter Budget Amount", Toast.LENGTH_LONG).show()
            }
        }
        return rootView
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}