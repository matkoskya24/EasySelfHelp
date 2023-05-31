package com.example.easyselfhelp.FitnessPackage.WorkoutResultPackage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.easyselfhelp.databinding.FragmentCreateWorkoutProgressResultBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.util.*


class CreateWorkoutProgressResult : Fragment() {
    private var _binding: FragmentCreateWorkoutProgressResultBinding? = null
    val binding get() = _binding!!
    private lateinit var dbRef: DatabaseReference
    private val viewModel: WorkoutResultViewModel by activityViewModels()
    private val currentDate = "${Calendar.getInstance().get(Calendar.MONTH) + 1}-${
        Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
    }-${Calendar.getInstance().get(Calendar.YEAR)}"

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
            val steps = binding.stepsEditText.text.toString()
            val hours = binding.hoursEditText.text.toString()
            val minutes = binding.minutesEditText.text.toString()
            val secs = binding.secondsEditText.text.toString()
            var newWorkoutResult = WorkoutResult()
            val durExists = ((hours != "") || minutes != "" || secs != "")
            if (calories != "" || steps != "" || durExists) {
                var viewNum = 0
                var caloriesInt = -1
                var stepsInt = -1
                val dur: String
                try {
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
                            if (hours.toInt() in 0..99) {
                                hoursInt = hours.toInt()
                            } else {
                                Toast.makeText(
                                    activity,
                                    "Please Enter Correct Hours",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }
                        if (minutes != "") {
                            if (minutes.toInt() in (0..59)) {
                                minsInt = minutes.toInt()
                            } else {
                                Toast.makeText(
                                    activity,
                                    "Please Enter Correct Minutes",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }
                        if (secs != "") {
                            if (secs.toInt() in (0..59)) {
                                secsInt = secs.toInt()
                            } else {
                                Toast.makeText(
                                    activity,
                                    "Please Enter Correct Seconds",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }
                        if (hoursInt >= 0 && minsInt >= 0 && secsInt >= 0) {
                            val hoursFormat: String = if (hoursInt < 10) {
                                "0${hoursInt}"
                            } else {
                                hoursInt.toString()
                            }
                            val minutesFormat: String = if (minsInt < 10) {
                                "0${minsInt}"
                            } else {
                                minsInt.toString()
                            }
                            val secsFormat: String = if (secsInt < 10) {
                                "0$secsInt"
                            } else {
                                secsInt.toString()
                            }
                            dur = "${hoursFormat}:${minutesFormat}:${secsFormat}"
                            viewNum++
                            newWorkoutResult = WorkoutResult(
                                currentDate,
                                caloriesInt,
                                stepsInt,
                                dur,
                                workoutResultID,
                                viewNum
                            )
                            dbRef.child("WorkoutResult").child(workoutResultID.toString())
                                .setValue(newWorkoutResult)
                            rootview.findNavController().navigateUp()
                        }

                    } else if (!durExists) {
                        dbRef.child("WorkoutResult").child(workoutResultID.toString())
                            .setValue(newWorkoutResult)
                        rootview.findNavController().navigateUp()
                    }

                } catch (NumberFormatException: Exception) {
                    Toast.makeText(activity, "Invalid Input, Please Try Again", Toast.LENGTH_LONG)
                        .show()
                }
            } else {
                Toast.makeText(activity, "Unknown Error", Toast.LENGTH_LONG).show()
            }
        }
        return rootview
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}