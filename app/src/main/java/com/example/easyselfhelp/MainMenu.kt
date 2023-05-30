package com.example.easyselfhelp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.easyselfhelp.databinding.FragmentMainMenuBinding

class MainMenu : Fragment() {
    private var _binding: FragmentMainMenuBinding? = null
    val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainMenuBinding.inflate(inflater, container, false)
        val rootView = binding.root
        binding.easyBudgetButton.setOnClickListener {
            val action = MainMenuDirections.actionMainMenuToBudgetFragment()
            rootView.findNavController().navigate(action)
        }
        binding.fitnessTrackerButton.setOnClickListener {
            val action = MainMenuDirections.actionMainMenuToFitnessResultListFragment()
            rootView.findNavController().navigate(action)
        }
        binding.homeworkPlannerButton.setOnClickListener {
            val action = MainMenuDirections.actionMainMenuToHomeworkPlannerFragment()
            rootView.findNavController().navigate(action)
        }
        binding.positiveQuotesButton.setOnClickListener {
            val action = MainMenuDirections.actionMainMenuToQuotesFragment()
            rootView.findNavController().navigate(action)
        }
        binding.readingListButton.setOnClickListener {
            val action = MainMenuDirections.actionMainMenuToReadingListFragment()
            rootView.findNavController().navigate(action)
        }
        return rootView
    }
}