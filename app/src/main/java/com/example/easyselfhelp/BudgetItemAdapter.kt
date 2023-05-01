package com.example.easyselfhelp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.easyselfhelp.databinding.BudgetItemLayoutBinding

class BudgetItemAdapter(val budgetItemList: MutableList<BudgetItem>):
    RecyclerView.Adapter<BudgetViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BudgetViewHolder {
        val binding = BudgetItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BudgetViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BudgetViewHolder, position: Int) {
        val currentBudgetItem = budgetItemList[position]
        if (!currentBudgetItem.isCompleted){
            holder.bindBudgetItem(currentBudgetItem)
        }
    }

    override fun getItemCount(): Int {
        return budgetItemList.size
    }

}