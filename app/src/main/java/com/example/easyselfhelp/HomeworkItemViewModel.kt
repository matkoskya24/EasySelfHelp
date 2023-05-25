package com.example.easyselfhelp

import androidx.lifecycle.ViewModel

class HomeworkItemViewModel: ViewModel() {
    private var _assignmentListLow: MutableList<HomeworkItem> = mutableListOf()
    private var _assignmentListHigh: MutableList<HomeworkItem> = mutableListOf()
    private var _assignmentList: MutableList<HomeworkItem> = mutableListOf()
    private var redFlagIDs: MutableList<Int> = mutableListOf()
    val assignmentList get() = _assignmentList
    fun addToList(homeworkItem: HomeworkItem){
        if(checkForDups(homeworkItem.assignmentID) || checkForRedFlag(homeworkItem.assignmentID)){
            removeFromList(homeworkItem.assignmentID, homeworkItem.highPriority?: false)
            if(checkForRedFlag(homeworkItem.assignmentID)){
                homeworkItem.isCompleted = true
            }
        }

            if (homeworkItem.highPriority == true && !homeworkItem.isCompleted) {
                _assignmentListHigh.add(homeworkItem)
            } else if(!homeworkItem.isCompleted) {
                _assignmentListLow.add(homeworkItem)
            }

            combineLists()

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
            if(_assignmentList[counter].assignmentID == id){
                _assignmentList.removeAt(counter)
            }
            counter++
        }
        counter = 0
        if(priority){
            while (counter < _assignmentListHigh.size){
                if(_assignmentListHigh[counter].assignmentID == id){
                    _assignmentListHigh.removeAt(counter)
                }
                counter++
            }
        }else{
            while (counter < _assignmentListLow.size){
                if(_assignmentListLow[counter].assignmentID == id){
                    _assignmentListLow.removeAt(counter)
                }
                counter++
            }
        }
    }
    private fun checkForDups(id: Int): Boolean{
        var counter = 0
        while(counter < _assignmentList.size){
            if(_assignmentList[counter].assignmentID == id){
                return true
            }
            counter++
        }
        return false
    }
    private fun checkForRedFlag(id: Int): Boolean{
        for(Int in redFlagIDs){
            if(id == Int){
                return true
            }
        }
        return false
    }
     fun removeDupsAdvanced(name: String, dueDate: String, priority: Boolean){
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
