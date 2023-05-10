package com.example.easyselfhelp

import androidx.recyclerview.widget.RecyclerView
import com.example.easyselfhelp.databinding.HomeworkItemLayoutBinding

class HomeworkItemViewHolder(val binding:HomeworkItemLayoutBinding):RecyclerView.ViewHolder(binding.root) {
    private lateinit var currentHomeworkItem: HomeworkItem
    fun bindHomeworkItem(homeworkItem: HomeworkItem){
     currentHomeworkItem = homeworkItem
        if(!currentHomeworkItem.isCompleted){
            binding.homeworkNameViewRecycler.text = currentHomeworkItem.assignmentName
            binding.dueDateViewRecycler.text = currentHomeworkItem.assignmentDueDate
            binding.
        }
    }
}