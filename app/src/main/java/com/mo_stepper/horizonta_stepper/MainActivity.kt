package com.mo_stepper.horizonta_stepper

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import com.mo_stepper.horizonta_stepper.databinding.ActivityMainBinding
import com.mo_stepper.mohorizontalstepper.MoHorizontalStepper


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setOnClicks()
    }

    private fun setOnClicks() {
        val stepper = binding.stepper
        binding.et.doAfterTextChanged {
            try {
                val numberOfSteps = it.toString().toInt()
                stepper.setNumberOfSteps(numberOfSteps)
                Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
            } catch (_: Exception) {
            }
        }

        binding.apply {
            btnCurrent.setOnClickListener {
                stepper.setStepperMode(MoHorizontalStepper.MoStepperMode.SELECT_CURRENT)
            }
            btnPrevious.setOnClickListener {
                stepper.setStepperMode(MoHorizontalStepper.MoStepperMode.SELECT_PREVIOUS)

            }
            btnBoth.setOnClickListener {
                stepper.setStepperMode(MoHorizontalStepper.MoStepperMode.SELECT_PREVIOUS_AND_CURRENT)
            }
        }

        binding.apply {
            btnRed.setOnClickListener {
                stepper.setSelectedBackgroundColor(R.color.red)
            }
            btnGreen.setOnClickListener {
                stepper.setSelectedBackgroundColor(R.color.green)
            }
            btnYellow.setOnClickListener {
                stepper.setSelectedBackgroundColor(R.color.yellow)
            }
        }

    }
}