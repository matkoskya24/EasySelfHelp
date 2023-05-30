package com.example.easyselfhelp.FitnessPackage.WorkoutResultPackage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.easyselfhelp.HomeworkItemPackage.HomeworkItemAdapter
import com.example.easyselfhelp.R
import com.example.easyselfhelp.databinding.FragmentTrackFitnessResultsBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
class FitnessResultListFragment : Fragment() {
    private var _binding: FragmentTrackFitnessResultsBinding? = null
    private val binding get() = _binding!!
    lateinit var dbRef: DatabaseReference
    private val viewModel: WorkoutResultViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTrackFitnessResultsBinding.inflate(inflater, container, false)
        val rootView = binding.root
        dbRef = Firebase.database.reference
        binding.addWorkoutResultButton.setOnClickListener {
            val action = FitnessResultListFragmentDirections.actionFitnessResultListFragmentToCreateWorkoutProgressResult()
            rootView.findNavController().navigate(action)
        }
        binding.clearWorkoutResultsButton.setOnClickListener {
            val action = FitnessResultListFragmentDirections.actionFitnessResultListFragmentToDeleteWorkoutProgressResultFragment()
            rootView.findNavController().navigate(action)
        }
        viewModel.workoutResultList.observe(viewLifecycleOwner) { newList ->
            val myAdapter = WorkoutResultAdapter(newList)
            binding.recyclerViewWorkoutResults.adapter = myAdapter
        }
        return rootView
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

