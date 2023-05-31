package com.example.easyselfhelp.FitnessPackage.WorkoutResultPackage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.easyselfhelp.databinding.FragmentDeleteWorkoutProgressResultBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class DeleteWorkoutProgressResultFragment : Fragment() {
    private var _binding: FragmentDeleteWorkoutProgressResultBinding? = null
    private val viewModel: WorkoutResultViewModel by activityViewModels()
    private lateinit var dbRef: DatabaseReference
    val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDeleteWorkoutProgressResultBinding.inflate(inflater, container, false)
        val rootview = binding.root
        dbRef = Firebase.database.reference
        binding.buttonNo.setOnClickListener {
            rootview.findNavController().navigateUp()
        }
        binding.buttonYes.setOnClickListener {
            dbRef.child("WorkoutResult").removeValue()
            viewModel.dropTables()
            binding.deleteWorkoutResultTitleView.text = "ALL WORKOUT RESULTS CLEARED"
            binding.buttonYes.isVisible = false
            binding.buttonNo.isVisible = false
            binding.goBack.isVisible = true
        }
        binding.goBack.setOnClickListener {
            rootview.findNavController().navigateUp()
        }

        return rootview
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}