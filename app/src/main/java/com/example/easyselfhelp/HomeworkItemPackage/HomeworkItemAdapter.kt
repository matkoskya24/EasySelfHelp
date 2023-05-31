package com.example.easyselfhelp.HomeworkItemPackage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.easyselfhelp.databinding.HomeworkItemLayoutBinding

class HomeworkItemAdapter(private val homeworkItemList: MutableList<HomeworkItem>) :
    RecyclerView.Adapter<HomeworkItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeworkItemViewHolder {
        val binding =
            HomeworkItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeworkItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeworkItemViewHolder, position: Int) {
        val currentHomeworkItem = homeworkItemList[position]
        if (!currentHomeworkItem.isCompleted) {
            holder.bindHomeworkItem(currentHomeworkItem)
        }
    }

    override fun getItemCount(): Int {
        return homeworkItemList.size
    }
}