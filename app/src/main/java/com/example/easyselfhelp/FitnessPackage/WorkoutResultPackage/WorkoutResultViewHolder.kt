package com.example.easyselfhelp.FitnessPackage.WorkoutResultPackage

import androidx.recyclerview.widget.RecyclerView
import com.example.easyselfhelp.databinding.WorkoutResultItemLayoutBinding

class WorkoutResultViewHolder(val binding: WorkoutResultItemLayoutBinding):RecyclerView.ViewHolder(binding.root) {
    private lateinit var currentWorkoutResult: WorkoutResult
    fun bindWorkoutResult(workoutResult: WorkoutResult){
        currentWorkoutResult = workoutResult
    }
}