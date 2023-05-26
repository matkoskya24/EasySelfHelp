package com.example.easyselfhelp.BudgetItemPackage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.easyselfhelp.databinding.FragmentBudgetItemDeleteBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class BudgetItemDeleteFragment : Fragment() {
    private var _binding: FragmentBudgetItemDeleteBinding? = null
    private val viewModel: BudgetViewModel by activityViewModels()
    val binding get() = _binding!!
    lateinit var dbRef: DatabaseReference
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBudgetItemDeleteBinding.inflate(inflater, container, false)
        val rootView = binding.root
        dbRef = Firebase.database.reference
        val args = BudgetItemDeleteFragmentArgs.fromBundle(requireArguments())
        viewModel.removeFromList(args.currentID)
        viewModel.addRedFlag(args.currentID)
        binding.acknowledgeButton.setOnClickListener {
            rootView.findNavController().navigateUp()
        }
        binding.clearAllBudgetItems.setOnClickListener{
            dbRef.child("BudgetItem").removeValue()
            viewModel.dropTables()
            binding.budgetItemDeleteTitleView.text = "ALL BUDGET ITEMS ARE NOW CLEARED"
        }

        return rootView
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}