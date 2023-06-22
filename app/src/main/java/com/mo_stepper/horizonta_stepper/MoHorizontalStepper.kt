package com.mo_stepper.horizonta_stepper

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.allViews

class MoHorizontalStepper @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val stepViews: MutableList<View> = mutableListOf()
    private val spaceViews: MutableList<View> = mutableListOf()
    private var currentStepIndex: Int = 1
    private var numberOfSteps = 4
    var stepClickListener: ((stepIndex: Int) -> Unit)? = null
    private var stepperMode: MoStepperMode = MoStepperMode.SELECT_CURRENT
    private var menu: Menu? = null

    init {
        orientation = HORIZONTAL
        stepClickListener = { stepIndex ->
            setCurrentStep(stepIndex)
        }
        initStepper()
    }

    private fun initStepper() {
        for (i in 0 until numberOfSteps) {
            val stepView = createStepView(i + 1)
            stepViews.add(stepView)
            addView(stepView)

            if (i != numberOfSteps - 1) {
                val space = createSpace()
                spaceViews.add(space)
                addView(space)
            }

        }
        updateStepViews()
    }

    private fun updateStepViews() {
        for ((index, stepView) in stepViews.withIndex()) {
            when (stepperMode) {
                MoStepperMode.SELECT_CURRENT -> {
                    setStepViewForSelectCurrentMode(index, stepView)
                }
                MoStepperMode.SELECT_PREVIOUS -> {
                    setStepViewForSelectPreviousMode(index, stepView)
                }
                MoStepperMode.SELECT_PREVIOUS_AND_CURRENT -> {
                    setStepViewForSelectPreviousAndCurrentMode(index, stepView)

                }
            }
        }

        for ((index, spaceView) in spaceViews.withIndex()) {
            val space = spaceView.findViewById<View>(R.id.framer)
            setSpaceForAllModes(index, space)
        }


    }

    /*
    methods for public use
     */


    fun moveToNextStep() {
        if (currentStepIndex < stepViews.size) {
            currentStepIndex++
            updateStepViews()
        }
    }

    fun moveToPreviousStep() {
        if (currentStepIndex > 0) {
            currentStepIndex--
            updateStepViews()
        }
    }

    fun setCurrentStep(stepIndex: Int) {
        if (stepIndex in 0 until stepViews.size + 1) {
            currentStepIndex = stepIndex
            updateStepViews()
        }
    }

    fun setNumberOfSteps(number: Int) {
        numberOfSteps = number
        reInvalidate()
    }

    fun setStepperMode(stepperMode: MoStepperMode) {
        this.stepperMode = stepperMode
    }

    fun setNavigationMenu(menu: Menu) {
        this.menu = menu
    }

    fun getNumberOfSteps(): Int = numberOfSteps

    fun getCurrentFragment(): Int? {
        return menu?.getItem(currentStepIndex)?.itemId
    }

    fun isLastStep(): Boolean = currentStepIndex == stepViews.size - 1

    /*
    methods for shaping and coloring
     */
    private fun setStepViewForSelectPreviousAndCurrentMode(index: Int, stepView: View) {
        val view = stepView.findViewById<ImageView>(R.id.checked_background)
        val text = stepView.findViewById<TextView>(R.id.tv_number)

        if (index <= currentStepIndex - 1) {
            //selected
            view.visibility = View.INVISIBLE
            text.setTextColor(ContextCompat.getColor(context, R.color.stepper_selected))
        } else {
            //not selected
            view.visibility = View.VISIBLE
            text.setTextColor(ContextCompat.getColor(context, R.color.stepper_not_selected))
        }
    }

    private fun setStepViewForSelectPreviousMode(index: Int, stepView: View) {
        val view = stepView.findViewById<ImageView>(R.id.checked_background)
        val text = stepView.findViewById<TextView>(R.id.tv_number)

        if (index < currentStepIndex - 1) {
            //selected
            view.visibility = View.INVISIBLE
            text.setTextColor(ContextCompat.getColor(context, R.color.stepper_selected))
        } else {
            //not selected
            view.visibility = View.VISIBLE
            text.setTextColor(ContextCompat.getColor(context, R.color.stepper_not_selected))
        }
    }

    private fun setStepViewForSelectCurrentMode(index: Int, stepView: View) {
        val view = stepView.findViewById<ImageView>(R.id.checked_background)
        val text = stepView.findViewById<TextView>(R.id.tv_number)

        if (index == currentStepIndex - 1) {
            //selected
            view.visibility = View.INVISIBLE
            text.setTextColor(ContextCompat.getColor(context, R.color.stepper_selected))
        } else {
            //not selected
            view.visibility = View.VISIBLE
            text.setTextColor(ContextCompat.getColor(context, R.color.stepper_not_selected))
        }
    }

    private fun createSpace(): View {
        val space = LayoutInflater.from(context).inflate(R.layout.space, this, false)
        return space
    }

    private fun createStepView(stepNumber: Int): View {
        val stepView = LayoutInflater.from(context).inflate(R.layout.step_view_layout, this, false)
        val txt = stepView.findViewById<TextView>(R.id.tv_number)
        txt.text = stepNumber.toString()
        stepView.setOnClickListener {
            stepClickListener?.invoke(stepNumber)
        }
        return stepView
    }

    private fun setSpaceForAllModes(index: Int, space: View) {
        if (index < currentStepIndex - 1) {
            //selected
            space.setBackgroundColor(ContextCompat.getColor(context, R.color.red))
        } else {
            //not selected
            space.setBackgroundColor(
                ContextCompat.getColor(
                    context,
                    R.color.control_theme_color
                )
            )
        }
    }


    private fun reInvalidate() {
        removeAllViews()
        stepViews.clear()
        spaceViews.clear()
        initStepper()
    }

    /*
    enum for modes
     */

    enum class MoStepperMode() {
        SELECT_PREVIOUS, SELECT_CURRENT, SELECT_PREVIOUS_AND_CURRENT
    }

}