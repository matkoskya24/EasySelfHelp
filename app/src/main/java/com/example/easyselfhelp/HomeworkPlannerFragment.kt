package com.example.easyselfhelp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.findNavController
import com.example.easyselfhelp.databinding.FragmentHomeworkPlannerFragmentBinding

class homework_planner_fragment : Fragment() {
    private var _binding: FragmentHomeworkPlannerFragmentBinding? = null
    val binding get() = _binding!!
    private val viewModel: HomeworkItemViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeworkPlannerFragmentBinding.inflate(inflater, container, false)
        val rootView = binding.root
        binding.addHomeworkItemButton.setOnClickListener {
            val action = homework_planner_fragmentDirections.actionHomeworkPlannerFragmentToAddHomeworkItemFragment()
            rootView.findNavController().navigate(action)
        }
        setFragmentResultListener("requestKeyHomework"){requestKeyHomework, bundle ->
            var newHomeworkItemBundle: Bundle? = bundle.getBundle("bundleKeyHomework")
            val assignmentName = newHomeworkItemBundle?.getString("homeworkAssignmentName")
            val assignmentDueDate = newHomeworkItemBundle?.getString("homeworkDueDate")
            val homeworkPriority = newHomeworkItemBundle?.getBoolean("homeworkPriority")
            val newHomeworkItem = HomeworkItem(assignmentName.toString(), assignmentDueDate.toString(), homeworkPriority, false, viewModel.assignmentID)
            viewModel.addToList(newHomeworkItem)
            viewModel.increaseID()
        }
        val myAdapter = HomeworkItemAdapter(viewModel.assignmentList)
        binding.recyclerViewHomework.adapter = myAdapter
        return rootView
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}