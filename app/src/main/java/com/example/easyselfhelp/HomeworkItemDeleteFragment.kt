package com.example.easyselfhelp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.easyselfhelp.databinding.FragmentHomeworkItemDeleteBinding


class HomeworkItemDeleteFragment : Fragment() {
    private var _binding: FragmentHomeworkItemDeleteBinding? = null
    private val viewModel: HomeworkItemViewModel by activityViewModels()
    val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeworkItemDeleteBinding.inflate(inflater, container, false)
        val rootview = binding.root
        val args = HomeworkItemDeleteFragmentArgs.fromBundle(requireArguments())
        binding.homeworkDeleteAcknowledgeButton.setOnClickListener {
            viewModel.removeFromList(args.id, args.priority)
            rootview.findNavController().navigateUp()
        }
        return rootview
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}