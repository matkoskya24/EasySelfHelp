package com.example.easyselfhelp.HomeworkItemPackage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.easyselfhelp.R
import com.example.easyselfhelp.databinding.FragmentAddHomeworkItemBinding
import com.google.firebase.database.DatabaseReference
import java.util.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class AddHomeworkItemFragment : Fragment() {
    private var _binding: FragmentAddHomeworkItemBinding? = null
    private val viewModel: HomeworkItemViewModel by activityViewModels()
    val binding get() = _binding!!
    lateinit var dbRef: DatabaseReference
    private var dueDay = 0
    private var dueMonth = 0
    private val currentMonth = Calendar.getInstance().get(Calendar.MONTH) + 1
    private val currentDate = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
    private val currentYear = Calendar.getInstance().get(Calendar.YEAR)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddHomeworkItemBinding.inflate(inflater, container, false)
        val rootview = binding.root
        dbRef = Firebase.database.reference
        spinnerSetup()
        binding.submitHomeworkButton.setOnClickListener {
            val assignmentName = binding.assignmentNameEditText.text.toString()
            val assignmentID = viewModel.generateNewID()
            var dueYearString = binding.dueYearEditText.text.toString()
            var dueYear = 0
            if(dueYearString != ""){
               dueYear = dueYearString.toInt()
            }
            val dateValid = checkIfDateValid(dueDay, dueMonth, dueYear)
            if(assignmentName != "" && dateValid) {
                val dueDate = "${dueMonth}-${dueDay}-${dueYear}"
                var highPriority: Boolean
                if (binding.highPriorityYes.isChecked) {
                    highPriority = true
                } else if (binding.highPriorityNo.isChecked) {
                    highPriority = false
                } else {
                    highPriority = false
                }
                val newHomeworkItem = HomeworkItem(assignmentName, dueDate, highPriority, false, assignmentID)
                dbRef.child("HomeworkItem").child(assignmentID.toString()).setValue(newHomeworkItem)
                rootview.findNavController().navigateUp()
            }else if(assignmentName == ""){
                Toast.makeText(activity, "Enter Assignment Name", Toast.LENGTH_LONG).show()
            }else if(!dateValid){
                if(dueYear > 9999 || dueYear < currentYear){
                    Toast.makeText(activity, "Enter Valid Year", Toast.LENGTH_LONG).show()
                }else if(dueMonth < currentMonth && dueYear <= currentYear){
                    Toast.makeText(activity, "Enter Valid Due Date", Toast.LENGTH_LONG).show()
                }else if(dueDay < currentDate && dueMonth <= currentMonth && dueYear <= currentYear){
                    Toast.makeText(activity, "Enter Valid Due Date", Toast.LENGTH_LONG).show()
                }
            }else{
                Toast.makeText(activity, "Unknown Error", Toast.LENGTH_LONG).show()
            }
        }
        return rootview
    }
    private fun spinnerSetup(){
        val monthArrayAdapter = ArrayAdapter.createFromResource(requireContext(),
            R.array.spinner_12_month, android.R.layout.simple_spinner_item)
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
        }else if(year > 9999 || year < currentYear){
            return false
        }else{
            return false
        }
    }

}