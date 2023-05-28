package com.example.easyselfhelp.BudgetItemPackage

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.findNavController
import com.example.easyselfhelp.databinding.FragmentBudgetFragmentBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class BudgetFragment : Fragment() {
    private var _binding: FragmentBudgetFragmentBinding? = null
    val binding get() = _binding!!
    lateinit var dbRef: DatabaseReference
    private val viewModel: BudgetViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBudgetFragmentBinding.inflate(inflater, container, false)
        val rootView = binding.root
        dbRef = Firebase.database.reference
        val myAdapter = BudgetItemAdapter(viewModel.budgetList)
        binding.recyclerViewBudget.adapter = myAdapter
        dbRef.child("BudgetItem").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val allDBentries = snapshot.children
                var numOfItemsAdded = 0
                for (singleBudgetItemEntry in allDBentries) {
                    numOfItemsAdded++
                    val name = singleBudgetItemEntry.child("name").getValue().toString()
                    val category = singleBudgetItemEntry.child("category").getValue().toString()
                    val amount =
                        singleBudgetItemEntry.child("amount").getValue().toString().toDouble()
                    val isCompleted =
                        singleBudgetItemEntry.child("isCompleted").getValue().toString()
                            ?.toBoolean()
                    val id = singleBudgetItemEntry.child("budgetID").getValue().toString().toInt()
                    val newBudgetItem = BudgetItem(name, category, amount, isCompleted ?: false, id)
                    viewModel.addToList(newBudgetItem)
                    myAdapter.notifyDataSetChanged()
                }
            }
            override fun onCancelled(error: DatabaseError) {
                Log.w("MainFragment", "Failed to read value.", error.toException())
            }
        })
        binding.addBudgetItem.setOnClickListener()
        {
            val action = BudgetFragmentDirections.actionBudgetFragmentToAddBudgetItemFragment()
            rootView.findNavController().navigate(action)
        }
        setFragmentResultListener("requestKey")
        { requestKey, bundle ->
            var newBudgetItemBundle: Bundle? = bundle.getBundle("bundleKey")
            val name = newBudgetItemBundle?.getString("budgetItemName")
            val category = newBudgetItemBundle?.getString("budgetItemCategory")
            val amount = newBudgetItemBundle?.getDouble("budgetItemAmount")
            val budgetID = viewModel.generateNewID()
            val newBudgetItem = BudgetItem(
                name.toString(), category.toString(),
                amount?.toDouble() ?: 0.0, false, budgetID
            )
            dbRef.child("BudgetItem").child(budgetID.toString()).setValue(newBudgetItem)
            viewModel.addToList(newBudgetItem)
            myAdapter.notifyDataSetChanged()
        }
        return rootView
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}