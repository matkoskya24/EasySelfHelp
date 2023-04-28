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
    private var budgetList: MutableList<BudgetItem> = mutableListOf(BudgetItem("", "", 0.0, true))
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBudgetFragmentBinding.inflate(inflater, container, false)
        val rootView = binding.root
        val myAdapter = BudgetItemAdapter(budgetList)
        binding.recyclerViewBudget.adapter = myAdapter
        binding.addBudgetItem.setOnClickListener {
            val action = BudgetFragmentDirections.actionBudgetFragmentToAddBudgetItemFragment()
            rootView.findNavController().navigate(action)
        }
        setFragmentResultListener("requestKey"){requestKey, bundle ->
            var newBudgetItem: BudgetItem = bundle.get("bundleKey") as BudgetItem
            budgetList.add(newBudgetItem)
            binding.recyclerViewBudget.adapter = myAdapter
        }
        return rootView
    }

}