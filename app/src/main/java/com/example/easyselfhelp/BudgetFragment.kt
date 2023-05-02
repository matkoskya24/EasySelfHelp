package com.example.easyselfhelp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.findNavController
import com.example.easyselfhelp.databinding.FragmentBudgetFragmentBinding
const val KEY_NAME_LIST = "list_of_names_key"
const val KEY_CATEGORY_LIST = "list_of_categories_key"
const val KEY_AMOUNT_LIST = "list_of_amounts_key"
const val KEY_COMPLETE_LIST = "list_of_completed_key"
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
            var newBudgetItemBundle: Bundle? = bundle.getBundle("bundleKey")
            val name = newBudgetItemBundle?.getString("budgetItemName")
            val category = newBudgetItemBundle?.getString("budgetItemCategory")
            val amount = newBudgetItemBundle?.getDouble("budgetItemAmount")
            val newBudgetItem = BudgetItem(name.toString(), category.toString(),
                amount?.toDouble() ?: 0.0, false )
            budgetList.add(newBudgetItem)
            val myAdapter = BudgetItemAdapter(budgetList)
            binding.recyclerViewBudget.adapter = myAdapter
        }
        return rootView
    }

    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        super.onSaveInstanceState(savedInstanceState)
        val nameList: ArrayList<String> = arrayListOf()
        val categoryList: ArrayList<String> = arrayListOf()
        val amountList: ArrayList<Double> = arrayListOf()
        val completedList: ArrayList<Boolean> = arrayListOf()
        var counter = 0
        while(counter < budgetList.size){
            nameList.add(budgetList[counter].name)
            categoryList.add(budgetList[counter].category)
            amountList.add(budgetList[counter].amount)
            completedList.add(budgetList[counter].isCompleted)
        }
        savedInstanceState.putStringArrayList(KEY_NAME_LIST, nameList)
        savedInstanceState.putStringArrayList(KEY_CATEGORY_LIST, categoryList)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}