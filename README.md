# Mo Horizontal Stepper (not done yet)
# a horizontal stepper with three modes of selection as shown 
https://github.com/Mohamed02Emad/Mo_Horizonta_Stepper/assets/81470639/0473c38a-37dd-403c-9de4-4ab4760e633a

default mode is SELECT_CURRENT
you can change the mode using `setStepperMode(MoHorizontalStepper.MoStepperMode.SELECT_CURRENT)`   

# available modes are

| Modes                                            | Usage                                                         |
| ------------------------------------------------ | --------------------------------------------------------------|
| `SELECT_CURRENT`                                 | MoHorizontalStepper.MoStepperMode.SELECT_CURRENT              |
| `SELECT_PREVIOUS`                                | MoHorizontalStepper.MoStepperMode.SELECT_CURRENT              |
| `SELECT_PREVIOUS_AND_CURRENT`                    | MoHorizontalStepper.MoStepperMode.SELECT_PREVIOUS_AND_CURRENT |

-------------------------------------------------------------------------------------------------------------------------

# you can change number of steps (max is 7)
https://github.com/Mohamed02Emad/Mo_Horizonta_Stepper/assets/81470639/92e4bc7f-461b-4e10-8268-4428c5599582

default number is 4 steps but you can change it using `setNumberOfSteps(numberOfSteps)`

# colora are adjustable too
https://github.com/Mohamed02Emad/Mo_Horizonta_Stepper/assets/81470639/7f204dd0-5d12-4555-bba1-4baf3b49112d

# here are color attributs \n
![colornames](https://github.com/Mohamed02Emad/Mo_Horizonta_Stepper/assets/81470639/c2662f99-68a1-443a-8fa0-5461876ea74d)

an example of changing selected text color to red
`setSelectedTextColor(R.drawable.red)`    

## table of public methods to use
| Methods                                          | Rwturn Type                                                  | Default               |
| ------------------------------------------------ | ------------------------------------------------------------ | --------------------- |
| `moveToPreviousStep`                             | Any                                                          | --                    |
| `moveToNextStep`                                 | Any                                                          | --                    |
| `setStepperMode`                                 | Any                                                          | SELECT_CURRENT        |
| `setCurrentStep`                                 | Any                                                          | 4                     |
| `setSelectedTextColor`                           | Any                                                          | white                 |
| `setSelectedBackgroundColor`                     | Any                                                          | red                   |
| `setNotSelectedBackgroundColor`                  | Any                                                          | white                 |
| `setNotSelectedTextColor`                        | Any                                                          | black                 |
| `setNotSelectedRingColor`                        | Any                                                          | red                   |
| `setCurrentSelectedRingColor`                    | Any                                                          | teel                  |
| `setSelectedSpacerColor`                         | Any                                                          | red                   |
| `setNotSelectedSpacerColor`                      | Any                                                          | black                 |
| `setNumberOfSteps`                               | Integer                                                      | 4                     |
| `getNumberOfSteps`                               | integer                                                      | 4                     |
| `isLastStep`                                     | Boalean                                                      | --                    |

# usage 

paste this to your xml
```xml
    <com.mo_stepper.horizonta_stepper.MoHorizontalStepper
        android:id="@+id/stepper"
        android:layout_width="match_parent"
        android:layout_height="wrap_content" />
```

now you need to access this stepper from your code and set it's attributes

```kotlin
       val stepper = findViewById<MoHorizontalStepper>(R.id.stepper)
        stepper.apply {
            setStepperMode(MoHorizontalStepper.MoStepperMode.SELECT_PREVIOUS_AND_CURRENT)
            setNumberOfSteps(5)
            //make sure not to call setBackgroundColor() but setSelectedBackgroundColor
            setSelectedBackgroundColor(R.color.red)
        }
```
