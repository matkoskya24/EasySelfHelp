package com.example.easyselfhelp.FitnessPackage.WorkoutResultPackage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.easyselfhelp.R
import com.example.easyselfhelp.databinding.FragmentDeleteWorkoutProgressResultBinding
import com.example.easyselfhelp.databinding.FragmentHomeworkItemDeleteBinding
import com.google.firebase.database.DatabaseReference

class DeleteWorkoutProgressResultFragment : Fragment() {
    private var _binding: FragmentDeleteWorkoutProgressResultBinding? = null
    private val viewModel: WorkoutResultViewModel by activityViewModels()
    lateinit var dbRef: DatabaseReference
    val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDeleteWorkoutProgressResultBinding.inflate(inflater, container, false)
        val rootview = binding.root
        return rootview
    }
}