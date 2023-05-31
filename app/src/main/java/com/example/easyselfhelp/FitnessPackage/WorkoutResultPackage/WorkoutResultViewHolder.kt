package com.example.easyselfhelp.FitnessPackage.WorkoutResultPackage

import androidx.recyclerview.widget.RecyclerView
import com.example.easyselfhelp.databinding.WorkoutResultItemLayoutBinding

class WorkoutResultViewHolder(val binding: WorkoutResultItemLayoutBinding):RecyclerView.ViewHolder(binding.root) {
    private lateinit var currentWorkoutResult: WorkoutResult
    fun bindWorkoutResult(workoutResult: WorkoutResult){
        currentWorkoutResult = workoutResult
        if(currentWorkoutResult.workoutID != -1 && currentWorkoutResult.viewNum > 0){
            binding.resultDateView.text = currentWorkoutResult.date
            val calorieText = "Cal: " + currentWorkoutResult.calories
            val stepText = "Steps: " + currentWorkoutResult.steps
            val dur = currentWorkoutResult.duration
            val viewNum = currentWorkoutResult.viewNum
            if(viewNum == 3){
                binding.progressDisplayOne.text = calorieText
                binding.progressDisplayMain.text = stepText
                binding.progressDisplayThree.text = dur
            }else if (viewNum == 2){
                if(currentWorkoutResult.calories > 0){
                    binding.progressDisplayOne.text = calorieText
                    if(currentWorkoutResult.steps > 0){
                        binding.progressDisplayThree.text = stepText
                    }else if(dur != ""){
                        binding.progressDisplayThree.text = dur
                    }
                }else if(currentWorkoutResult.steps > 0){
                    binding.progressDisplayOne.text = stepText
                    if(currentWorkoutResult.calories > 0){
                        binding.progressDisplayThree.text = calorieText
                    }else if(dur != ""){
                        binding.progressDisplayThree.text = dur
                    }
                }else if(dur != ""){
                    binding.progressDisplayThree.text = dur
                }
            }else if(viewNum == 1){
                if (currentWorkoutResult.calories > 0){
                    binding.progressDisplayMain.text = calorieText
                }else if(currentWorkoutResult.steps > 0){
                    binding.progressDisplayMain.text = stepText
                }else if(dur != ""){
                    binding.progressDisplayMain.text = dur
                }
            }
        }
    }
}