package com.example.easyselfhelp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.findNavController
import com.example.easyselfhelp.databinding.FragmentBudgetFragmentBinding

class BudgetFragment : Fragment() {
    private var _binding:FragmentBudgetFragmentBinding? = null
    val binding get() = _binding!!
    private var budgetList: MutableList<BudgetItem> = mutableListOf()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBudgetFragmentBinding.inflate(inflater, container, false)
        val rootView = binding.root
        binding.addBudgetItem.setOnClickListener {
            val action = BudgetFragmentDirections.actionBudgetFragmentToAddBudgetItemFragment()
            rootView.findNavController().navigate(action)
        }
        setFragmentResultListener("requestKey"){requestKey, bundle ->
            var newBudgetItemName: String = bundle.getString("bundleKey").toString()
            var newBudgetItem: BudgetItem = BudgetItem(newBudgetItemName, "category", 20.0, false)
            budgetList.add(newBudgetItem)
            val myAdapter = BudgetItemAdapter(budgetList)
            binding.recyclerViewBudget.adapter = myAdapter
        }
        return rootView
    }

}