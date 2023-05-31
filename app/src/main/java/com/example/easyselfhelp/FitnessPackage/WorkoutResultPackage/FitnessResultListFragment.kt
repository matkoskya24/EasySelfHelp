package com.example.easyselfhelp.FitnessPackage.WorkoutResultPackage

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.easyselfhelp.databinding.FragmentTrackFitnessResultsBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class FitnessResultListFragment : Fragment() {
    private var _binding: FragmentTrackFitnessResultsBinding? = null
    private val binding get() = _binding!!
    private lateinit var dbRef: DatabaseReference
    private val viewModel: WorkoutResultViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTrackFitnessResultsBinding.inflate(inflater, container, false)
        val rootView = binding.root
        dbRef = Firebase.database.reference
        binding.addWorkoutResultButton.setOnClickListener {
            val action =
                FitnessResultListFragmentDirections.actionFitnessResultListFragmentToCreateWorkoutProgressResult()
            rootView.findNavController().navigate(action)
        }
        binding.clearWorkoutResultsButton.setOnClickListener {
            val action =
                FitnessResultListFragmentDirections.actionFitnessResultListFragmentToDeleteWorkoutProgressResultFragment()
            rootView.findNavController().navigate(action)
        }
        viewModel.workoutResultList.observe(viewLifecycleOwner) { newList ->
            val myAdapter = WorkoutResultAdapter(newList)
            binding.recyclerViewWorkoutResults.adapter = myAdapter
        }
        dbRef.child("WorkoutResult").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val listFromDB: MutableList<WorkoutResult> = mutableListOf()
                val allDBentries = snapshot.children
                var numOfWorkoutResultsAdded = 0
                for (singleWorkoutResultEntry in allDBentries) {
                    numOfWorkoutResultsAdded++
                    val date = singleWorkoutResultEntry.child("date").value.toString()
                    val calories =
                        singleWorkoutResultEntry.child("calories").value.toString().toInt()
                    val steps = singleWorkoutResultEntry.child("steps").value.toString().toInt()
                    val duration = singleWorkoutResultEntry.child("duration").value.toString()
                    val workoutID =
                        singleWorkoutResultEntry.child("workoutID").value.toString().toInt()
                    val viewNum = singleWorkoutResultEntry.child("viewNum").value.toString().toInt()
                    val newWorkoutResult =
                        WorkoutResult(date, calories, steps, duration, workoutID, viewNum)
                    listFromDB.add(newWorkoutResult)
                    viewModel.syncList(listFromDB)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w("MainFragment", "Failed to read value.", error.toException())
            }

        })
        return rootView
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

