package com.example.easyselfhelp.FitnessPackage.WorkoutResultPackage

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WorkoutResultViewModel : ViewModel() {
    private val _workoutResultList: MutableLiveData<MutableList<WorkoutResult>> = MutableLiveData(
        mutableListOf()
    )
    private var redFlagIDs: MutableList<Int> = mutableListOf(-1)
    val workoutResultList get() = _workoutResultList
    fun syncList(list: MutableList<WorkoutResult>) {
        var counter = 0
        while (counter < list.size) {
            if (checkForRedFlags(list[counter].workoutID)) {
                list.removeAt(counter)
            }
            counter++
        }
        _workoutResultList.value = list
    }

    private fun checkForRedFlags(id: Int): Boolean {
        for (int in redFlagIDs) {
            if (id == int) {
                return true
            }
        }
        return false
    }

    fun dropTables() {
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

    private fun checkForDups(id: Int): Boolean {
        var counter = 0
        while (counter < (_workoutResultList.value?.size ?: 0)) {
            if (_workoutResultList.value?.get(counter)?.workoutID == id) {
                return true
            }
            counter++
        }
        return false
    }

}