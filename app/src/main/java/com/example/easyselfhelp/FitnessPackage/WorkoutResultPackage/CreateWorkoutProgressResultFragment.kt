package com.example.easyselfhelp.FitnessPackage.WorkoutResultPackage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.easyselfhelp.R
import com.example.easyselfhelp.databinding.FragmentCreateWorkoutProgressResultBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.util.*


class CreateWorkoutProgressResult : Fragment() {
    private var _binding: FragmentCreateWorkoutProgressResultBinding? = null
    val binding get() = _binding!!
    lateinit var dbRef: DatabaseReference
    private val viewModel: WorkoutResultViewModel by activityViewModels()
    private val currentDate = "${Calendar.getInstance().get(Calendar.DAY_OF_MONTH)}-${Calendar.getInstance().get(Calendar.MONTH) + 1}-${Calendar.getInstance().get(Calendar.YEAR)}"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCreateWorkoutProgressResultBinding.inflate(inflater, container, false)
        val rootview = binding.root
        dbRef = Firebase.database.reference
        binding.submitWorkoutResultButton.setOnClickListener {
            val workoutResultID = viewModel.generateNewID()
            val calories = binding.caloriesEditText.text.toString()
            val steps = binding.caloriesEditText.text.toString()
            var hours = binding.hoursEditText.text.toString()
            var minutes = binding.minutesEditText.text.toString()
            var secs = binding.secondsEditText.text.toString()
            val durExists = ((hours != "") || minutes != "" || secs != "")
            if (calories != "" || steps != "" || durExists) {
                var viewNum = 0
                var caloriesInt = -1
                var stepsInt = -1
                var dur = ""
                if (calories != "") {
                    caloriesInt = calories.toInt()
                    viewNum++
                }
                if (steps != "") {
                    stepsInt = steps.toInt()
                    viewNum++
                }
                if (durExists) {
                    var hoursInt = -1
                    var minsInt = -1
                    var secsInt = -1
                    if (hours != "") {
                        if(hours.toInt() >= 0) {
                            hoursInt = hours.toInt()
                        }else{
                            //TODO add toast
                        }
                    }
                    if (minutes != "") {
                        if(minutes.toInt() >= 0 && minutes.toInt() < 60) {
                            minsInt = minutes.toInt()
                        }else{
                            //TODO add toast
                        }
                    }
                    if(secs != ""){
                        if(secs.toInt() >=0 && secs.toInt() < 60){
                            secsInt = secs.toInt()
                        }else{
                            //TODO add toast
                        }
                    }
                    if(hoursInt >=0 && minsInt >=0 && secsInt >= 0){
                        var hoursFormat = ""
                        var minutesFormat = ""
                        var secsFormat = ""
                        if(hoursInt < 10){
                            hoursFormat = "0${hoursInt}"
                        }else{
                            hoursFormat = hoursInt.toString()
                        }
                        if(minsInt < 10){
                            minutesFormat = "0${minsInt}"
                        }else{
                            minutesFormat = minsInt.toString()
                        }
                        if(secsInt < 10){
                            secsFormat = "0${secsInt.toString()}"
                        }else{
                            secsFormat = secsInt.toString()
                        }
                        dur = "${hoursFormat}:${minutesFormat}:${secsFormat}"
                        viewNum++
                    }
                }
                val newWorkoutResult = WorkoutResult(currentDate, caloriesInt, stepsInt, dur, workoutResultID, viewNum)
                dbRef.child("WorkoutResult").child(workoutResultID.toString()).setValue(newWorkoutResult)
            }else{
                //TODO make toast
            }
        }
        return rootview
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}