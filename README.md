# Mo Horizontal Stepper

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
	        implementation 'com.github.Mohamed02Emad:Mo_Horizonta_Stepper:1.0.3'
	}
 ```

# a horizontal stepper with three modes of selection as shown 
https://github.com/Mohamed02Emad/Mo_Horizonta_Stepper/assets/81470639/0473c38a-37dd-403c-9de4-4ab4760e633a

default mode is SELECT_CURRENT
you can change the mode using `setStepperMode(MoHorizontalStepper.MoStepperMode.SELECT_CURRENT)`   

available modes (can only chance them from code)

| Modes                                            | Usage                                                         |
| ------------------------------------------------ | --------------------------------------------------------------|
| `SELECT_CURRENT`                                 | MoHorizontalStepper.MoStepperMode.SELECT_CURRENT              |
| `SELECT_PREVIOUS`                                | MoHorizontalStepper.MoStepperMode.SELECT_CURRENT              |
| `SELECT_PREVIOUS_AND_CURRENT`                    | MoHorizontalStepper.MoStepperMode.SELECT_PREVIOUS_AND_CURRENT |

-------------------------------------------------------------------------------------------------------------------------

you can change number of steps (max is 7)

https://github.com/Mohamed02Emad/Mo_Horizonta_Stepper/assets/81470639/92e4bc7f-461b-4e10-8268-4428c5599582

default number is 4 steps but you can change it using `setNumberOfSteps(numberOfSteps)`

colors are adjustable too

https://github.com/Mohamed02Emad/Mo_Horizonta_Stepper/assets/81470639/7f204dd0-5d12-4555-bba1-4baf3b49112d

# here are color attributs
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

            //rest of you initialization
        }
```

or you can access some attributes from your xml (those are all attributes that can be changed from xml)

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

## How to use it to navigate

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
       val myMenu = menuInflater.inflate(R.menu.menu_main, menu)
        stepper.apply {
            setStepperMode(MoHorizontalStepper.MoStepperMode.SELECT_PREVIOUS_AND_CURRENT)
            setNumberOfSteps(make sure they are same number as your menu items)
            setNavigationMenu(myMenu)
        }
```

3- now you need to setUp the clickListener to give you the fragment that you clicked on navigate to 

```kotlin
        stepper.stepClickListener={stepIndex ->
            val fragmentId = stepper.getFragmentByIndex(stepIndex)
            fragmentId?.let {destination ->
                val navController = findNavController()
                navController.navigate(destination, null, null, null)
                stepper.setCurrentStep(stepIndex)
            }
        }
```
