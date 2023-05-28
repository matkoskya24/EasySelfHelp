package com.example.easyselfhelp.FitnessPackage.WorkoutResultPackage

import androidx.lifecycle.MutableLiveData

class WorkoutResultViewModel {
    private val _workoutResultList: MutableLiveData<MutableList<WorkoutResult>> = MutableLiveData(
        mutableListOf()
    )
    private var redFlagIDs: MutableList<Int> = mutableListOf()
    val workoutResultList get() = _workoutResultList
    fun syncList(list: MutableList<WorkoutResult>){
        var counter = 0
        while(counter < list.size){
            if(checkForRedFlags(list[counter].workoutID) || list[counter].isDeleted){
                list.removeAt(counter)
            }
            counter++
        }
        _workoutResultList.value = list
    }
    fun checkForRedFlags(id: Int): Boolean{
        for(int in redFlagIDs){
            if(id == int){
                return true
            }
        }
        return false
    }
    fun removeFromList(id: Int){
        var counter = 0
        while(counter < _workoutResultList.value?.size ?: 0){
            if(_workoutResultList.value?.get(counter)?.workoutID ?: -1 == id){
                _workoutResultList.value?.removeAt(counter)
            }
            counter++
        }
    }
    fun addRedFlag(id:Int){
        redFlagIDs.add(id)
    }
    fun dropTables(){
        _workoutResultList.value = mutableListOf()
        redFlagIDs = mutableListOf()
    }
    fun generateNewID(): Int {
        var randId: Int = kotlin.random.Random.nextInt()
        while (randId == -1 || checkForDups(randId)) {
            randId = kotlin.random.Random.nextInt()
        }
        return randId
    }
    private fun checkForDups(id: Int): Boolean{
        var counter = 0
        while(counter < _workoutResultList.value?.size ?: 0){
            if (_workoutResultList.value?.get(counter)?.workoutID == id) {
                return true
            }
            counter++
        }
        return false
    }

}