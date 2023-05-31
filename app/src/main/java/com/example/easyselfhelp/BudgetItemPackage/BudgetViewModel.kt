package com.example.easyselfhelp.BudgetItemPackage

import androidx.lifecycle.ViewModel

class BudgetViewModel : ViewModel() {
    private var _budgetList: MutableList<BudgetItem> = mutableListOf()
    private var redFlagIDs: MutableList<Int> = mutableListOf(-1)
    val budgetList get() = _budgetList
    fun addToList(budgetItem: BudgetItem) {
        if (checkForDups(budgetItem.budgetID)) {
            removeFromList(budgetItem.budgetID)
        } else {
            removeDupsAdvanced(budgetItem.name, budgetItem.category, budgetItem.amount)
        }
        if (!checkForRedFlag(budgetItem.budgetID)) {
            _budgetList.add(budgetItem)
        }
    }

    fun removeFromList(id: Int) {
        var counter = 0
        while (counter < _budgetList.size) {
            if (_budgetList[counter].budgetID == id) {
                _budgetList.removeAt(counter)
            }
            counter++
        }
    }

    fun dropTables() {
        _budgetList = mutableListOf()
        redFlagIDs = mutableListOf()
    }

    fun addRedFlag(id: Int) {
        redFlagIDs.add(id)
    }

    fun generateNewID(): Int {
        var randId: Int = kotlin.random.Random.nextInt()
        while (randId == -1 || checkForDups(randId)) {
            randId = kotlin.random.Random.nextInt()
        }
        return randId
    }

    private fun checkForDups(id: Int): Boolean {
        var counter = 0
        while (counter < _budgetList.size) {
            if (_budgetList[counter].budgetID == id) {
                return true
            }
            counter++
        }
        return false
    }

    private fun checkForRedFlag(id: Int): Boolean {
        var counter = 0
        while (counter < redFlagIDs.size) {
            if (id == redFlagIDs[counter]) {
                return true
            }
            counter++
        }
        return false
    }

    private fun removeDupsAdvanced(name: String, category: String, amount: Double) {
        var counter = 0
        while (counter < _budgetList.size) {
            if (_budgetList[counter].name == name && _budgetList[counter].category == category && _budgetList[counter].amount == amount) {
                _budgetList.removeAt(counter)
            }
            counter++
        }
    }
}