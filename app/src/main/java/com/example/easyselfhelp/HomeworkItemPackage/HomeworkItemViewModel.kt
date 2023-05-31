package com.example.easyselfhelp.HomeworkItemPackage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeworkItemViewModel : ViewModel() {
    private val _assignmentList: MutableLiveData<MutableList<HomeworkItem>> =
        MutableLiveData(mutableListOf())

    private var redFlagIDs: MutableList<Int> = mutableListOf()
    val assignmentList: LiveData<MutableList<HomeworkItem>>
        get() = _assignmentList

    fun syncList(list: MutableList<HomeworkItem>) {
        var counter = 0
        while (counter < list.size) {
            if (checkForRedFlag(list[counter].assignmentID) || list[counter].isCompleted) {
                list.removeAt(counter)
            }
            counter++
        }
        list.sortByDescending { it.highPriority }
        _assignmentList.value = list

    }

    fun generateNewID(): Int {
        var randId: Int = kotlin.random.Random.nextInt()
        while (randId == -1 || checkForDups(randId)) {
            randId = kotlin.random.Random.nextInt()
        }
        return randId
    }

    fun addRedFlag(id: Int) {
        redFlagIDs.add(id)
    }

    fun dropTables() {
        _assignmentList.value = mutableListOf()
        redFlagIDs = mutableListOf()
    }

    fun removeFromList(id: Int) {
        var counter = 0
        while (counter < (_assignmentList.value?.size ?: 0)) {
            if (_assignmentList.value?.get(counter)?.assignmentID == id) {
                _assignmentList.value?.removeAt(counter)
            }
            counter++
        }
    }

    private fun checkForRedFlag(id: Int): Boolean {
        for (Int in redFlagIDs) {
            if (id == Int) {
                return true
            }
        }
        return false
    }

    private fun checkForDups(id: Int): Boolean {
        var counter = 0
        while (counter < (_assignmentList.value?.size ?: 0)) {
            if (_assignmentList.value?.get(counter)?.assignmentID == id) {
                return true
            }
            counter++
        }
        return false
    }

}
