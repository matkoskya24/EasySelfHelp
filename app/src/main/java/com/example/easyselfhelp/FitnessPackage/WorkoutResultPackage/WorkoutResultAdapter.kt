package com.example.easyselfhelp.FitnessPackage.WorkoutResultPackage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.easyselfhelp.databinding.WorkoutResultItemLayoutBinding

class WorkoutResultAdapter(val workoutResultList: MutableList<WorkoutResult>):
    RecyclerView.Adapter<WorkoutResultViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkoutResultViewHolder {
        val binding = WorkoutResultItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WorkoutResultViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WorkoutResultViewHolder, position: Int) {
        val currentWorkoutItem = workoutResultList[position]
        if(!(currentWorkoutItem.workoutID == -1)){
            holder.bindWorkoutResult(currentWorkoutItem)
        }
    }

    override fun getItemCount(): Int {
        return workoutResultList.size
    }

}