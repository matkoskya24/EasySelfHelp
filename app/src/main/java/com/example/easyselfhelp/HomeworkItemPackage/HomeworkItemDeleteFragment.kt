package com.example.easyselfhelp.HomeworkItemPackage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.easyselfhelp.databinding.FragmentHomeworkItemDeleteBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class HomeworkItemDeleteFragment : Fragment() {
    private var _binding: FragmentHomeworkItemDeleteBinding? = null
    private val viewModel: HomeworkItemViewModel by activityViewModels()
    private lateinit var dbRef: DatabaseReference
    val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeworkItemDeleteBinding.inflate(inflater, container, false)
        val rootview = binding.root
        dbRef = Firebase.database.reference
        val args = HomeworkItemDeleteFragmentArgs.fromBundle(requireArguments())
        dbRef.child("HomeworkItem").child(args.id.toString()).removeValue()
        viewModel.removeFromList(args.id)
        viewModel.addRedFlag(args.id)
        binding.homeworkDeleteAcknowledgeButton.setOnClickListener {
            rootview.findNavController().navigateUp()
        }
        binding.homeworkItemDropTables.setOnClickListener {
            binding.homeworkItemDeleteViewTitleView.text = "ALL HOMEWORK ITEMS ARE NOW CLEARED"
            dbRef.child("HomeworkItem").removeValue()
            viewModel.dropTables()
        }
        return rootview
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}