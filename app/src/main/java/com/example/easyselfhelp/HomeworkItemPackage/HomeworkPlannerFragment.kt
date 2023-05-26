package com.example.easyselfhelp.HomeworkItemPackage

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.easyselfhelp.databinding.FragmentHomeworkPlannerFragmentBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class homework_planner_fragment : Fragment() {
    private var _binding: FragmentHomeworkPlannerFragmentBinding? = null
    val binding get() = _binding!!
    lateinit var dbRef: DatabaseReference
    private val viewModel: HomeworkItemViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeworkPlannerFragmentBinding.inflate(inflater, container, false)
        val rootView = binding.root
        dbRef = Firebase.database.reference
        binding.addHomeworkItemButton.setOnClickListener {
            val action =
                homework_planner_fragmentDirections.actionHomeworkPlannerFragmentToAddHomeworkItemFragment()
            rootView.findNavController().navigate(action)
        }


        viewModel.assignmentList.observe(viewLifecycleOwner) { newList ->
            val myAdapter = HomeworkItemAdapter(newList)
            binding.recyclerViewHomework.adapter = myAdapter
        }
        dbRef.child("HomeworkItem").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val listFromDB : MutableList<HomeworkItem> = mutableListOf()
                val allDBentries = snapshot.children
                var numOfHomeworkItemsAdded = 0
                for (singleHomeworkItemEntry in allDBentries) {
                    numOfHomeworkItemsAdded++
                    val assignmentName =
                        singleHomeworkItemEntry.child("assignmentName").getValue().toString()
                    val assignmentDueDate =
                        singleHomeworkItemEntry.child("assignmentDueDate").getValue().toString()
                    val highPriority =
                        singleHomeworkItemEntry.child("highPriority").getValue().toString()
                            .toBoolean()
                    val isCompleted =
                        singleHomeworkItemEntry.child("isCompleted").getValue().toString()
                            .toBoolean()
                    val id =
                        singleHomeworkItemEntry.child("assignmentID").getValue().toString().toInt()
                    val newHomeworkItem = HomeworkItem(
                        assignmentName,
                        assignmentDueDate,
                        highPriority,
                        isCompleted,
                        id
                    )
                    listFromDB.add(newHomeworkItem)
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