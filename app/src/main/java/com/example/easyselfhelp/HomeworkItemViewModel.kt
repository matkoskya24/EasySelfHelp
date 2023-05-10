package com.example.easyselfhelp

import androidx.lifecycle.ViewModel

class HomeworkItemViewModel: ViewModel() {
    private var _assignmentListTemp: MutableList<HomeworkItem> = mutableListOf()
    private var _assignmentList: MutableList<HomeworkItem> = mutableListOf()
    val assignmentList get() = _assignmentList
    private var _assignmentId = 0
    val assignmentID get() = _assignmentId
    fun addToList(homeworkItem: HomeworkItem){
        _assignmentListTemp.add(homeworkItem)
        organizeList()
    }
    fun increaseID(){
        _assignmentId++
    }
    fun removeFromList(id: Int){
        var counter = 0
        while(counter < _assignmentList.size){
            if(_assignmentList[counter].id == id){
                _assignmentList.removeAt(counter)
            }else if(_assignmentListTemp[counter].id == id){
                _assignmentListTemp.removeAt(counter)
            }
        }
    }
    private fun organizeList(){
        if(_assignmentList.size > 0){
            var counter = 0
            var highPriorityCount = 0
            while (counter < _assignmentListTemp.size){
                if(_assignmentListTemp[counter].highPriority){
                    _assignmentList.add(highPriorityCount, _assignmentListTemp[counter])
                    highPriorityCount++
                }
            }
            counter = 0
            while (counter < _assignmentListTemp.size){
                if(!_assignmentListTemp[counter].highPriority){
                    _assignmentList.add(_assignmentListTemp[counter])
                }
            }
        }else{
            for(HomeworkItem in _assignmentListTemp){
                _assignmentList.add(HomeworkItem)
            }
        }
    }
}