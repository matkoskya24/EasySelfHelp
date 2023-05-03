package com.example.easyselfhelp

import androidx.lifecycle.ViewModel
import androidx.fragment.app.viewModels

class BudgetViewModel: ViewModel() {
    private var _budgetList: MutableList<BudgetItem> = mutableListOf()
    val budgetList get() = _budgetList
    fun addToList(budgetItem: BudgetItem){
        _budgetList.add(budgetItem)
    }
}