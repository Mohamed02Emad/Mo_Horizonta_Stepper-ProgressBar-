# Mo Horizontal Stepper

# setup

to use it make sure to include this to your project:

Add it in your root build.gradle 
```gradle
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
 ```


```gradle
dependencies {
	        implementation 'com.github.Mohamed02Emad:Mo_Horizonta_Stepper:1.0.5'
	}
 ```


# Info

modes :

https://github.com/Mohamed02Emad/Mo_Horizonta_Stepper/assets/81470639/0473c38a-37dd-403c-9de4-4ab4760e633a

default mode is `SELECT_CURRENT`
* you can change the mode using 

`setStepperMode(MoHorizontalStepper.MoStepperMode.SELECT_CURRENT)`   

available modes (can only chance them from code)

| Modes                                            | Usage                                                         |
| ------------------------------------------------ | --------------------------------------------------------------|
| `SELECT_CURRENT`                                 | MoHorizontalStepper.MoStepperMode.SELECT_CURRENT              |
| `SELECT_PREVIOUS`                                | MoHorizontalStepper.MoStepperMode.SELECT_PREVIOUS             |
| `SELECT_PREVIOUS_AND_CURRENT`                    | MoHorizontalStepper.MoStepperMode.SELECT_PREVIOUS_AND_CURRENT |

-------------------------------------------------------------------------------------------------------------------------

steps :

you can change number of steps (max is 7)

https://github.com/Mohamed02Emad/Mo_Horizonta_Stepper/assets/81470639/92e4bc7f-461b-4e10-8268-4428c5599582

default number is 4 steps but you can change it using `setNumberOfSteps(numberOfSteps)` or from xml 

colors :

https://github.com/Mohamed02Emad/Mo_Horizonta_Stepper/assets/81470639/7f204dd0-5d12-4555-bba1-4baf3b49112d

each color name is in this image

![colornames](https://github.com/Mohamed02Emad/Mo_Horizonta_Stepper/assets/81470639/c2662f99-68a1-443a-8fa0-5461876ea74d)

* an example of changing selected text color to red
`setSelectedTextColor(R.color.red)`    

methods from code :

## table of public methods to use
| Methods                           | Return Type           | Parameters            |  Default          | description                                      |
| --------------------------------- | --------------------- | --------------------- | ----------------- | ------------------------------------------------ |
| `moveToPreviousStep`              | Any                   | --                    |  --               | moves the stepper to the previous step           |
| `moveToNextStep`                  | Any                   | --                    |  --               | moves the stepper to the next step               |
| `setStepperMode`                  | Any                   | MoStepperMode         |  SELECT_CURRENT   | change stepper mode to one of the above          |
| `setCurrentStep`                  | Any                   | Integer               |  4                | move stepper to this exact step                  |
| `setSelectedTextColor`            | Any                   | Integer               |  white            | takes `R.colors.color` to set mentioned color    |
| `setSelectedBackgroundColor`      | Any                   | Integer               |  red              | takes `R.colors.color` to set mentioned color    |
| `setNotSelectedBackgroundColor`   | Any                   | Integer               |  white            | takes `R.colors.color` to set mentioned color    |
| `setNotSelectedTextColor`         | Any                   | Integer               |  black            | takes `R.colors.color` to set mentioned color    |
| `setNotSelectedRingColor`         | Any                   | Integer               |  red              | takes `R.colors.color` to set mentioned color    |
| `setCurrentSelectedRingColor`     | Any                   | Integer               |  teal             | takes `R.colors.color` to set mentioned color    |
| `setSelectedSpacerColor`          | Any                   | Integer               |  red              | takes `R.colors.color` to set mentioned color    |
| `setNotSelectedSpacerColor`       | Any                   | Integer               |  black            | takes `R.colors.color` to set mentioned color    |
| `getPreviousFragment`             | Integer               | --                    |  --               | return id of previous fragment, null if no prev  |
| `getNextFragment`                 | Integer               | --                    |  --               | return id of next fragment, null if no next      |
| `getCurrentStepIndex`             | Integer               | --                    |  --               | return the index of current step                 |
| `setNumberOfSteps`                | Integer               | Integer               |  4                | set number of steps in the stepper               |
| `getNumberOfSteps`                | integer               | --                    |  4                | return total number of steps in the stepper      |
| `isLastStep`                      | Boalean               | --                    |  --               | return true if last step is selected else false  |
| `isFirstStep`                     | Boalean               | --                    |  --               | return true if first step is selected else false |

---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------


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

            //rest of you initialization
        }
```

or you can access some attributes from your xml ( here are attributes that can be changed from xml )

```xml
       <com.mo_stepper.horizonta_stepper.MoHorizontalStepper
        android:id="@+id/stepper"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:selectedTextColor=""
        app:selectedBackgroundColor=""
        app:notSelectedBackgroundColor=""
        app:notSelectedTextColor=""
        app:notSelectedRingColor=""
        app:currentSelectedRingColor=""
        app:selectedSpacerColor=""
        app:notSelectedSpacerColor=""
        app:numberOfSteps="" />
```

## use it to navigate

1- create navhost fragment with navGraph

```xml
    <androidx.fragment.app.FragmentContainerView
        android:id="@+id/frame_stepper"
        android:name="androidx.navigation.fragment.NavHostFragment"
        android:layout_width="match_parent"
        android:layout_height="0dp"
        app:navGraph="@navigation/stepper_navigation" />
 ```

2- in your code pass a menu and make sure that menu has sasme size as number of steps, 
if number of elements are diffrent that number of steps you will get null as a result from the stepper

```kotlin
       val stepper = findViewById<MoHorizontalStepper>(R.id.stepper)
       val navHostFragment = findViewById<FragmentContainerView>(R.id.frame_stepper)
       val myMenu = MenuBuilder(this)
        menuInflater.inflate(R.menu.menu, myMenu)
        stepper.apply {
            setStepperMode(MoHorizontalStepper.MoStepperMode.SELECT_PREVIOUS_AND_CURRENT)
            setNumberOfSteps(make sure they are same number as your menu items)
            setNavigationMenu(myMenu)
        }
```

3- now you need to setUp the clickListener to give you the fragment that you clicked on navigate to 

```kotlin
        stepper.stepClickListener={stepIndex ->
            val fragmentId = stepper.getFragmentByIndex(stepIndex-1)
            fragmentId?.let {destination ->
                val navController = navHostFragment.findNavController()
                navController.navigate(destination, null, null, null)
                stepper.setCurrentStep(stepIndex)
            }
        }
```

# Notes

1- if you used `moveToNextStep` or `moveToPreviousStep` you have to navigate manualy after that

2- if you are using the `MoStepperMode.SELECT_PREVIOUS` mode , there is an additional color called `currentSelected` which indicates the current step color 
and you can change it using `setCurrentSelectedRingColor(R.colors.color)`

3- currently steps have fixed size , I'll fix it in the next release

# have fun
