package com.example.easyselfhelp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.findNavController
import com.example.easyselfhelp.databinding.FragmentHomeworkPlannerFragmentBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import kotlin.math.sin

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
        val myAdapter = HomeworkItemAdapter(viewModel.assignmentList)
        binding.recyclerViewHomework.adapter = myAdapter
        dbRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val allDBentries = snapshot.children
                var numOfHomeworkItemsAdded = 0
                for(allHomeworkItemEntries in allDBentries){
                    for(singleHomeworkItemEntry in allHomeworkItemEntries.children){
                        numOfHomeworkItemsAdded++
                        val assignmentName = singleHomeworkItemEntry.child("assignmentName").getValue().toString()
                        val assignmentDueDate = singleHomeworkItemEntry.child("assignmentDueDate").getValue().toString()
                        val highPriority = singleHomeworkItemEntry.child("highPriority").getValue().toString().toBoolean()
                        val isCompleted = singleHomeworkItemEntry.child("isCompleted").getValue().toString().toBoolean()
                        val id = singleHomeworkItemEntry.child("id").getValue().toString().toInt()
                        val newHomeworkItem = HomeworkItem(assignmentName, assignmentDueDate, highPriority, isCompleted, id)
                        viewModel.addToList(newHomeworkItem)
                        myAdapter.notifyDataSetChanged()
                    }
                }
            }
            override fun onCancelled(error: DatabaseError) {
                Log.w("MainFragment", "Failed to read value.", error.toException())
            }
        })
        binding.addHomeworkItemButton.setOnClickListener {
            val action = homework_planner_fragmentDirections.actionHomeworkPlannerFragmentToAddHomeworkItemFragment()
            rootView.findNavController().navigate(action)
        }
        setFragmentResultListener("requestKey"){requestKey, bundle ->
            var newHomeworkItemBundle: Bundle? = bundle.getBundle("bundleKey")
            val assignmentName = newHomeworkItemBundle?.getString("assignmentName")
            val dueDate = newHomeworkItemBundle?.getString("dueDate")
            val highPriority = newHomeworkItemBundle?.getBoolean("highPriority")
            val isCompleted = newHomeworkItemBundle?.getBoolean("isCompleted")
            val id = newHomeworkItemBundle?.getInt("id")
            val newHomeworkItem = HomeworkItem(assignmentName?:"", dueDate?:"", highPriority, isCompleted?: false, id?: -1)
            dbRef.child("HomeworkItem").push().setValue(newHomeworkItem)
            viewModel.addToList(newHomeworkItem)
            myAdapter.notifyDataSetChanged()
        }
        return rootView
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}