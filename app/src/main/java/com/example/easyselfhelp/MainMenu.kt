package com.example.easyselfhelp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.easyselfhelp.databinding.FragmentMainMenuBinding
private const val CHANNEL_URL = "https://www.youtube.com/user/TEDxTalks"
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
        binding.readingListButton.setOnClickListener {
            val action = MainMenuDirections.actionMainMenuToReadingListFragment()
            rootView.findNavController().navigate(action)
        }
        binding.selfHelpVideoButton.setOnClickListener {
            val youtubeURI = Uri.parse(CHANNEL_URL)
            val websiteIntent = Intent(Intent.ACTION_VIEW, youtubeURI)
            binding.selfHelpVideoButton.context.startActivity(websiteIntent)
        }
        binding.meetTheDevButton.setOnClickListener {
            val action = MainMenuDirections.actionMainMenuToAboutMeFragment()
            rootView.findNavController().navigate(action)
        }
        return rootView
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}