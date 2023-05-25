package com.example.easyselfhelp

import androidx.lifecycle.ViewModel

class HomeworkItemViewModel: ViewModel() {
    private var _assignmentListLow: MutableList<HomeworkItem> = mutableListOf()
    private var _assignmentListHigh: MutableList<HomeworkItem> = mutableListOf()
    private var _assignmentList: MutableList<HomeworkItem> = mutableListOf()
    private var redFlagIDs: MutableList<Int> = mutableListOf(-1)
    val assignmentList get() = _assignmentList
    fun addToList(homeworkItem: HomeworkItem){
        if(checkForDups(homeworkItem.id)){
            removeFromList(homeworkItem.id, homeworkItem.highPriority?: false)
        }else{
            removeDupsAdvanced(homeworkItem.assignmentName, homeworkItem.assignmentDueDate, homeworkItem.highPriority?: false)
        }
        if(checkForRedFlag(homeworkItem.id)) {
            if (homeworkItem.highPriority == true) {
                _assignmentListHigh.add(homeworkItem)
            } else {
                _assignmentListLow.add(homeworkItem)
            }
            combineLists()
        }
    }
    private fun combineLists(){
        _assignmentList = mutableListOf()
        var counter = 0
        if(_assignmentListHigh.size > 0) {
            while(counter < _assignmentListHigh.size){
                _assignmentList.add(_assignmentListHigh[counter])
                counter++
            }
        }
        counter = 0
        while(counter < _assignmentListLow.size){
            _assignmentList.add(_assignmentListLow[counter])
            counter++
        }
    }
    fun generateNewID(): Int{
        var randId: Int = kotlin.random.Random.nextInt()
        while(randId==-1 || checkForDups(randId)){
            randId = kotlin.random.Random.nextInt()
        }
        return  randId
    }
    fun addRedFlag(id: Int){
        redFlagIDs.add(id)
    }
    fun dropTables(){
        _assignmentList = mutableListOf()
        _assignmentListHigh = mutableListOf()
        _assignmentListLow = mutableListOf()
        redFlagIDs = mutableListOf()
    }
    fun removeFromList(id: Int, priority: Boolean){
        var counter = 0
        while(counter < _assignmentList.size){
            if(_assignmentList[counter].id == id){
                _assignmentList.removeAt(counter)
            }
            counter++
        }
        counter = 0
        if(priority){
            while (counter < _assignmentListHigh.size){
                if(_assignmentListHigh[counter].id == id){
                    _assignmentListHigh.removeAt(counter)
                }
                counter++
            }
        }else{
            while (counter < _assignmentListLow.size){
                if(_assignmentListLow[counter].id == id){
                    _assignmentListLow.removeAt(counter)
                }
                counter++
            }
        }
    }
    private fun checkForDups(id: Int): Boolean{
        var counter = 0
        while(counter < _assignmentList.size){
            if(_assignmentList[counter].id == id){
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
    private fun removeDupsAdvanced(name: String, dueDate: String, priority: Boolean){
        var counter = 0
        while (counter<_assignmentList.size){
            if(_assignmentList[counter].assignmentName == name && _assignmentList[counter].assignmentDueDate == dueDate && _assignmentList[counter].highPriority == priority){
                _assignmentList.removeAt(counter)
            }
            counter++
        }
        counter == 0
        if(priority){
            while (counter < _assignmentListHigh.size){
                if(_assignmentListHigh[counter].assignmentName == name && _assignmentListHigh[counter].assignmentDueDate == dueDate && _assignmentListHigh[counter].highPriority == priority){
                    _assignmentListHigh.removeAt(counter)
                }
                counter++
            }
        }else{
            while (counter < _assignmentListLow.size){
                if(_assignmentListLow[counter].assignmentName == name && _assignmentListLow[counter].assignmentDueDate == dueDate && _assignmentListLow[counter].highPriority == priority){
                    _assignmentListLow.removeAt(counter)
                }
                counter++
            }
        }
    }
}
