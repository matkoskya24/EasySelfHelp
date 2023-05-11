package com.example.easyselfhelp

import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.easyselfhelp.databinding.HomeworkItemLayoutBinding


class HomeworkItemViewHolder(val binding:HomeworkItemLayoutBinding):RecyclerView.ViewHolder(binding.root) {
    private lateinit var currentHomeworkItem: HomeworkItem
    fun bindHomeworkItem(homeworkItem: HomeworkItem){
     currentHomeworkItem = homeworkItem
        if(!currentHomeworkItem.isCompleted){
            binding.homeworkNameViewRecycler.text = currentHomeworkItem.assignmentName
            binding.dueDateViewRecycler.text = currentHomeworkItem.assignmentDueDate
            if (currentHomeworkItem.highPriority == true){
                binding.highPriorityView.text = "HIGH PRIORITY"
            }
        }
    }
    init {
        binding.homeworkItemDeleteButton.setOnClickListener {
            currentHomeworkItem.isCompleted = true
            bindHomeworkItem(currentHomeworkItem)
            val action = homework_planner_fragmentDirections.actionHomeworkPlannerFragmentToHomeworkItemDeleteFragment(currentHomeworkItem.id)
            binding.root.findNavController().navigate(action)
        }
    }
}