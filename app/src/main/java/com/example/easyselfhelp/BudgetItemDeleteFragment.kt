package com.example.easyselfhelp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.easyselfhelp.databinding.FragmentBudgetItemDeleteBinding


class BudgetItemDeleteFragment : Fragment() {
    private var _binding: FragmentBudgetItemDeleteBinding? = null
    private val viewModel: BudgetViewModel by activityViewModels()
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBudgetItemDeleteBinding.inflate(inflater, container, false)
        val rootView = binding.root
        val args = BudgetItemDeleteFragmentArgs.fromBundle(requireArguments())
        binding.acknowledgeButton.setOnClickListener {
            viewModel.removeFromList(args.currentID)
            rootView.findNavController().navigateUp()
        }

        return rootView
    }

}