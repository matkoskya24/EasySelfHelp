package com.example.easyselfhelp

import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.easyselfhelp.databinding.BudgetItemLayoutBinding

class BudgetViewHolder(val binding:BudgetItemLayoutBinding ): RecyclerView.ViewHolder(binding.root) {
    private lateinit var currentBudgetItem: BudgetItem
    fun bindBudgetItem(budgetItem: BudgetItem){
        currentBudgetItem = budgetItem
        if (!currentBudgetItem.isCompleted) {
            binding.budgetNameTextView.text = currentBudgetItem.name
            binding.budgetCategoryTextView.text = "Category: " + currentBudgetItem.category
            binding.budgetAmountTextView.text = "Amount: $%.2f".format(currentBudgetItem.amount)
        }
    }
    init {
        binding.budgetDeleteButton.setOnClickListener{
            currentBudgetItem.isCompleted = true
            bindBudgetItem(currentBudgetItem)
            val action = BudgetFragmentDirections.actionBudgetFragmentToBudgetItemDeleteFragment(currentBudgetItem.id)
            binding.root.findNavController().navigate(action)
        }
    }

}