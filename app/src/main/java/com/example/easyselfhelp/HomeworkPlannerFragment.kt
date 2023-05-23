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
        val myAdapter = HomeworkItemAdapter(viewModel.assignmentList)
        binding.recyclerViewHomework.adapter = myAdapter
        return rootView
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}