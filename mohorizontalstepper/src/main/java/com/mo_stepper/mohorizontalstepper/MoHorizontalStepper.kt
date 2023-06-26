package com.mo_stepper.mohorizontalstepper

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat


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
    private var isClickable = true

    private var selectedTextColor: Int = 0
    private var selectedBackgroundColor: Int = 0

    private var notSelectedBackgroundColor: Int = 0
    private var notSelectedTextColor: Int = 0
    private var notSelectedRingColor: Int = 0

    private var currentSelectedRingColor: Int = 0

    private var selectedSpacerColor: Int = 0
    private var notSelectedSpacerColor: Int = 0

    init {
        val typedArray = context.obtainStyledAttributes(
            attrs, R.styleable.MoHorizontalStepper, defStyleAttr, 0
        )

        selectedTextColor = typedArray.getColor(
            R.styleable.MoHorizontalStepper_selectedTextColor,
            ContextCompat.getColor(context, R.color.stepper_white)
        )
        selectedBackgroundColor = typedArray.getColor(
            R.styleable.MoHorizontalStepper_selectedBackgroundColor,
            ContextCompat.getColor(context, R.color.stepper_red)
        )


        notSelectedBackgroundColor = typedArray.getColor(
            R.styleable.MoHorizontalStepper_notSelectedBackgroundColor,
            ContextCompat.getColor(context, R.color.stepper_white)
        )
        notSelectedRingColor = typedArray.getColor(
            R.styleable.MoHorizontalStepper_notSelectedRingColor,
            ContextCompat.getColor(context, R.color.stepper_red)
        )
        notSelectedTextColor = typedArray.getColor(
            R.styleable.MoHorizontalStepper_notSelectedTextColor,
            ContextCompat.getColor(context, R.color.stepper_black)
        )

        currentSelectedRingColor = typedArray.getColor(
            R.styleable.MoHorizontalStepper_currentSelectedRingColor,
            ContextCompat.getColor(context, R.color.stepper_teal_200)
        )
        numberOfSteps = typedArray.getInt(R.styleable.MoHorizontalStepper_numberOfSteps, 4)

        selectedSpacerColor = typedArray.getColor(
            R.styleable.MoHorizontalStepper_selectedSpacerColor,
            ContextCompat.getColor(context, R.color.stepper_red)
        )

        notSelectedSpacerColor = typedArray.getColor(
            R.styleable.MoHorizontalStepper_notSelectedSpacerColor,
            ContextCompat.getColor(context, R.color.stepper_black)
        )

        typedArray.recycle()
        orientation = HORIZONTAL
        stepClickListener = { stepIndex ->
        }
        initStepper()
    }

    private fun initStepper() {
        for (i in 0 until numberOfSteps) {
            val stepView = createStepView(i + 1)
            stepViews.add(stepView)
            Log.d("mohamed", "initStepper: 2 ")
            addView(stepView)

            if (i != numberOfSteps - 1) {
                Log.d("mohamed", "initStepper: 3 ")
                val space = createSpace()
                spaceViews.add(space)
                addView(space)
            }

        }

        updateStepViews()
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
        numberOfSteps = if (number > 7) 7 else number
        reInvalidate()
    }

    fun setStepperMode(stepperMode: MoStepperMode) {
        this.stepperMode = stepperMode
        updateStepViews()
    }

    fun setNavigationMenu(menu: Menu) {
        this.menu = menu
    }

    fun setSelectedTextColor(color: Int) {
        selectedTextColor = ContextCompat.getColor(context, color)
        updateStepViews()
    }

    fun setSelectedBackgroundColor(color: Int) {
        selectedBackgroundColor = ContextCompat.getColor(context, color)
        updateStepViews()
    }

    fun setNotSelectedBackgroundColor(color: Int) {
        notSelectedBackgroundColor = ContextCompat.getColor(context, color)
        updateStepViews()
    }

    fun setNotSelectedTextColor(color: Int) {
        notSelectedTextColor = ContextCompat.getColor(context, color)
        updateStepViews()
    }

    fun setNotSelectedRingColor(color: Int) {
        notSelectedRingColor = ContextCompat.getColor(context, color)
        updateStepViews()
    }

    fun setCurrentSelectedRingColor(color: Int) {
        currentSelectedRingColor = ContextCompat.getColor(context, color)
        updateStepViews()
    }

    fun setSelectedSpacerColor(color: Int) {
        selectedSpacerColor = ContextCompat.getColor(context, color)
        updateStepViews()
    }

    fun setNotSelectedSpacerColor(color: Int) {
        notSelectedSpacerColor = ContextCompat.getColor(context, color)
        updateStepViews()
    }


    fun getNumberOfSteps(): Int = numberOfSteps

    /*
    returns null if doesnot exist
     */
    fun getCurrentFragment(): Int? {
        return try {
            menu?.getItem(currentStepIndex)?.itemId
        } catch (_: java.lang.Exception) {
            null
        }
    }

    fun isLastStep(): Boolean = currentStepIndex == stepViews.size

    fun isFirstStep(): Boolean = currentStepIndex == 1

    fun getFragmentByIndex(stepIndex: Int): Int? {
        return try {
            menu?.getItem(stepIndex)?.itemId
        } catch (_: java.lang.Exception) {
            null
        }
    }

    fun getPreviousFragment(): Int? =
        if (currentStepIndex == 1) {
            null
        } else {
            menu?.getItem(currentStepIndex - 1)?.itemId
        }


    fun getNextFragment(): Int? =
        if (currentStepIndex == stepViews.size) {
            null
        } else {
            menu?.getItem(currentStepIndex + 1)?.itemId
        }

    fun getCurrentStepIndex(): Int {
        return currentStepIndex
    }

    fun setIsStepClickable(isClickable: Boolean) {
        this.isClickable = isClickable
    }

     fun isStepClickable(): Boolean = isClickable


    /*
    methods for shaping and coloring
     */

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

    private fun setStepViewForSelectPreviousAndCurrentMode(index: Int, stepView: View) {
        val view = stepView.findViewById<ImageView>(R.id.checked_background)
        val mostBackView = stepView.findViewById<ImageView>(R.id.most_back_view)
        val text = stepView.findViewById<TextView>(R.id.tv_number)

        if (index <= currentStepIndex - 1) {
            //selected
            view.visibility = View.INVISIBLE
            mostBackView.setBackgroundColor(selectedBackgroundColor)
            text.setTextColor(selectedTextColor)
        } else {
            //not selected
            view.visibility = View.VISIBLE
            view.setBackgroundColor(notSelectedBackgroundColor)
            mostBackView.setBackgroundColor(notSelectedRingColor)
            text.setTextColor(notSelectedTextColor)
        }
    }

    private fun setStepViewForSelectPreviousMode(index: Int, stepView: View) {
        val view = stepView.findViewById<ImageView>(R.id.checked_background)
        val mostBackView = stepView.findViewById<ImageView>(R.id.most_back_view)
        val text = stepView.findViewById<TextView>(R.id.tv_number)

        if (index < currentStepIndex - 1) {
            //selected
            view.visibility = View.INVISIBLE
            mostBackView.setBackgroundColor(selectedBackgroundColor)
            text.setTextColor(selectedTextColor)
        } else if (index == currentStepIndex - 1) {
            view.visibility = View.VISIBLE
            view.setBackgroundColor(notSelectedBackgroundColor)
            mostBackView.setBackgroundColor(currentSelectedRingColor)
            text.setTextColor(notSelectedTextColor)
        } else {
            //not selected
            view.visibility = View.VISIBLE
            view.setBackgroundColor(notSelectedBackgroundColor)
            mostBackView.setBackgroundColor(notSelectedRingColor)
            text.setTextColor(notSelectedTextColor)
        }
    }

    private fun setStepViewForSelectCurrentMode(index: Int, stepView: View) {
        val view = stepView.findViewById<ImageView>(R.id.checked_background)
        val mostBackView = stepView.findViewById<ImageView>(R.id.most_back_view)
        val text = stepView.findViewById<TextView>(R.id.tv_number)

        if (index == currentStepIndex - 1) {
            //selected
            view.visibility = View.INVISIBLE
            mostBackView.setBackgroundColor(selectedBackgroundColor)
            text.setTextColor(selectedTextColor)
        } else {
            //not selected
            view.visibility = View.VISIBLE
            view.setBackgroundColor(notSelectedBackgroundColor)
            mostBackView.setBackgroundColor(notSelectedRingColor)
            text.setTextColor(notSelectedTextColor)
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
            if (isClickable) {
                setCurrentStep(stepNumber)
                stepClickListener?.invoke(stepNumber)
            }
        }
        return stepView
    }

    private fun setSpaceForAllModes(index: Int, space: View) {
        if (index < currentStepIndex - 1) {
            //selected
            space.setBackgroundColor(selectedSpacerColor)
        } else {
            //not selected
            space.setBackgroundColor(notSelectedSpacerColor)
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
