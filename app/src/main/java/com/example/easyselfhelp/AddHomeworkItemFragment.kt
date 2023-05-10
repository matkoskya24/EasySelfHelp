package com.example.easyselfhelp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.findNavController
import com.example.easyselfhelp.databinding.FragmentAddHomeworkItemBinding


class AddHomeworkItemFragment : Fragment() {
    private var _binding: FragmentAddHomeworkItemBinding? = null
    val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddHomeworkItemBinding.inflate(inflater, container, false)
        val rootview = binding.root
        binding.submitHomeworkButton.setOnClickListener {
            val assignmentName = binding.assignmentNameEditText.text.toString()
            val dueDate = "${binding.dueMonthEditText.text}-${binding.dueMonthEditText.text}-${binding.dueYearEditText.text}".toString()
            var highPriority: Boolean = false
            if(binding.highPriorityYes.isChecked){
                highPriority = true
            }
            val resultBundle = bundleOf()
            resultBundle.putString("homeworkAssignmentName", assignmentName)
            resultBundle.putString("homeworkDueDate", dueDate)
            resultBundle.putBoolean("homeworkPriority", highPriority)
            setFragmentResult("requestKey", bundleOf("bundleKey" to resultBundle))
            rootview.findNavController().navigateUp()
        }
        return rootview
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}