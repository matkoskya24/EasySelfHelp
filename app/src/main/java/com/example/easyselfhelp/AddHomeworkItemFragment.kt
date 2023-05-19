package com.example.easyselfhelp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.findNavController
import com.example.easyselfhelp.databinding.FragmentAddHomeworkItemBinding
import java.time.LocalDate
import java.time.MonthDay
import java.time.Year
import java.util.*


class AddHomeworkItemFragment : Fragment() {
    private var _binding: FragmentAddHomeworkItemBinding? = null
    val binding get() = _binding!!
    private var dueDay = 0
    private var dueMonth = 0
    private val currentMonth = Calendar.MONTH
    private val currentDate = Calendar.DAY_OF_MONTH
    private val currentYear = Calendar.YEAR
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddHomeworkItemBinding.inflate(inflater, container, false)
        val rootview = binding.root
        spinnerSetup()
        binding.submitHomeworkButton.setOnClickListener {
            val assignmentName = binding.assignmentNameEditText.text.toString()
            val dateValid = checkIfDateValid(dueDay, dueMonth, binding.dueYearEditText.text.toString().toInt())
            if(dateValid && assignmentName != null) {
                val dueDate = "${dueMonth}-${dueDay}-${binding.dueYearEditText.text}"
                var highPriority: Boolean
                if (binding.highPriorityYes.isChecked) {
                    highPriority = true
                } else if (binding.highPriorityNo.isChecked) {
                    highPriority = false
                } else {
                    highPriority = false
                }
                val resultBundle = bundleOf()
                resultBundle.putString("homeworkAssignmentName", assignmentName)
                resultBundle.putString("homeworkDueDate", dueDate)
                resultBundle.putBoolean("homeworkPriority", highPriority)
                setFragmentResult(
                    "requestKeyHomework",
                    bundleOf("bundleKeyHomework" to resultBundle)
                )
                rootview.findNavController().navigateUp()
            }else if(!dateValid){

            }
        }
        return rootview
    }
    private fun spinnerSetup(){
        val monthArrayAdapter = ArrayAdapter.createFromResource(requireContext(), R.array.spinner_12_month, android.R.layout.simple_spinner_item)
        monthArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
        binding.monthSpinner.adapter = monthArrayAdapter
        binding.monthSpinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                adapterView: AdapterView<*>,
                childView: View?,
                position: Int,
                itemId: Long
            ){
                dueMonth = adapterView.getItemAtPosition(position).toString().toInt()
                dateSpinnerSetup(dueMonth)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                dateSpinnerSetup(1)
            }
        }
    }
    private fun dateSpinnerSetup(month: Int){
        var arrayId: Int
        if(month == 2){
            arrayId = R.array.spinner_29_day
        }else if(month == 4 || month == 6 || month == 9 || month == 11){
            arrayId = R.array.spinner_30_day
        }else{
            arrayId = R.array.spinner_31_day
        }
        val dateArrayAdapter = ArrayAdapter.createFromResource(requireContext(), arrayId, android.R.layout.simple_spinner_item)
        dateArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
        binding.daySpinner.adapter = dateArrayAdapter
        binding.daySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                adapterView: AdapterView<*>,
                childView: View?,
                position: Int,
                itemId: Long
            ){
                dueDay = adapterView.getItemAtPosition(position).toString().toInt()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun checkIfDateValid(day: Int, month: Int, year: Int): Boolean{
        if(day >= currentDate && month >= currentMonth && year >= currentYear){
            return true
        }else if(day <= currentDate && month > currentMonth && year >= currentYear){
            return true
        }else if(day <= currentDate && month <= currentMonth && year > currentYear){
            return true
        }else{
            return false
        }
    }
    private fun diagnoseDate(date: Int, id: Int): Boolean{
        if(id == 1){

        }
    }
}