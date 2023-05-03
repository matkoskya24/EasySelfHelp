package com.example.easyselfhelp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.easyselfhelp.databinding.FragmentBudgetFragmentBinding
import kotlin.Array as Array1
class BudgetFragment : Fragment() {
    private var _binding:FragmentBudgetFragmentBinding? = null
    val binding get() = _binding!!
    private val viewModel: BudgetViewModel by activityViewModels()
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
            var newBudgetItemBundle: Bundle? = bundle.getBundle("bundleKey")
            val name = newBudgetItemBundle?.getString("budgetItemName")
            val category = newBudgetItemBundle?.getString("budgetItemCategory")
            val amount = newBudgetItemBundle?.getDouble("budgetItemAmount")
            val newBudgetItem = BudgetItem(name.toString(), category.toString(),
                amount?.toDouble() ?: 0.0, false, viewModel.budgetID)
            viewModel.addToList(newBudgetItem)
            viewModel.increaseID()
        }
        val myAdapter = BudgetItemAdapter(viewModel.budgetList)
        binding.recyclerViewBudget.adapter = myAdapter
        return rootView
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}