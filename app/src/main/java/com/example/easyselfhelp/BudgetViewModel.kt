package com.example.easyselfhelp

import androidx.lifecycle.ViewModel
import androidx.fragment.app.viewModels

class BudgetViewModel: ViewModel() {
    private var _budgetList: MutableList<BudgetItem> = mutableListOf()
    val budgetList get() = _budgetList
    private var _budgetID = 0
    val budgetID get() = _budgetID
    fun addToList(budgetItem: BudgetItem){
        _budgetList.add(budgetItem)
    }
    fun increaseID(){
        _budgetID++
    }
    fun removeFromList(id: Int){
        var counter = 0
        while(counter < _budgetList.size){
            if(_budgetList[counter].id == id){
                _budgetList.removeAt(counter)
            }
        }
    }
}