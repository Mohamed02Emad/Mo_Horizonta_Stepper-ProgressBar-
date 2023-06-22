package com.mo_stepper.horizonta_stepper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import com.mo_stepper.horizonta_stepper.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setOnClicks()
    }

    private fun setOnClicks() {
        val stepper =  binding.stepper
        binding.et.doAfterTextChanged {
            try {
            val numberOfSteps = if (it.toString().toInt() > 6) 6 else it.toString().toInt()
            binding.stepper.setNumberOfSteps(numberOfSteps)
                Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
            }catch (_:Exception){ }
        }
    }
}