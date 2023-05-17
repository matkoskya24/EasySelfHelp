package com.example.easyselfhelp

import androidx.lifecycle.ViewModel

class HomeworkItemViewModel: ViewModel() {
    private var _assignmentListLow: MutableList<HomeworkItem> = mutableListOf()
    private var _assignmentListHigh: MutableList<HomeworkItem> = mutableListOf()
    private var _assignmentList: MutableList<HomeworkItem> = mutableListOf()
    val assignmentList get() = _assignmentList
    private var _assignmentId = 0
    val assignmentID get() = _assignmentId
    fun addToList(homeworkItem: HomeworkItem){
        if(checkForDups(homeworkItem.id)){
            removeFromList(homeworkItem.id, homeworkItem.highPriority?: false)
        }else{
            removeDupsAdvanced(homeworkItem.assignmentName, homeworkItem.assignmentDueDate, homeworkItem.highPriority?: false)
        }
       if(homeworkItem.highPriority == true){
           _assignmentListHigh.add(homeworkItem)
       }else{
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
    fun increaseID(){
        _assignmentId++
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
