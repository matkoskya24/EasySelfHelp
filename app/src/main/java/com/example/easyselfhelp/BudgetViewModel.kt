package com.example.easyselfhelp

import androidx.lifecycle.ViewModel
import androidx.fragment.app.viewModels
import kotlin.random.Random as Random1

class BudgetViewModel: ViewModel() {
    private var _budgetList: MutableList<BudgetItem> = mutableListOf()
    private var redFlagIDs: MutableList<Int> = mutableListOf(-1)
    val budgetList get() = _budgetList
    fun addToList(budgetItem: BudgetItem){
        if(checkForDups(budgetItem.id)){
            removeFromList(budgetItem.id)
        }else{
            removeDupsAdvanced(budgetItem.name, budgetItem.category, budgetItem.amount)
        }
        if(!checkForRedFlag(budgetItem.id)) {
            _budgetList.add(budgetItem)
        }
    }
    fun removeFromList(id: Int){
        var counter = 0
        while(counter < _budgetList.size){
            if(_budgetList[counter].id == id){
                _budgetList.removeAt(counter)
            }
        }
    }
    fun dropTables(){
        _budgetList = mutableListOf()
        redFlagIDs = mutableListOf()
    }
    fun addRedFlag(id: Int){
        redFlagIDs.add(id)
    }
    fun generateNewID(): Int{
        var randId: Int = kotlin.random.Random.nextInt()
        while(randId==-1 || checkForDups(randId)){
            randId = kotlin.random.Random.nextInt()
        }
        return  randId
    }
    private fun checkForDups(id: Int): Boolean{
        var counter = 0
        while(counter < _budgetList.size){
            if(_budgetList[counter].id == id){
                return true
            }
            counter++
        }
        return false
    }
    private fun checkForRedFlag(id: Int): Boolean{
        var counter = 0
            while (counter < redFlagIDs.size){
                if(id == redFlagIDs[counter]){
                    return true
                }
            }
        return false
    }
    private fun removeDupsAdvanced(name: String, category: String, amount: Double){
        var counter = 0
        while(counter < _budgetList.size){
            if(_budgetList[counter].name == name && _budgetList[counter].category == category && _budgetList[counter].amount == amount){
                _budgetList.removeAt(counter)
            }
            counter++
        }
    }
}