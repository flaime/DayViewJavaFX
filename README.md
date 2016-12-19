# DayViewJavaFX
DayViewJavaFX is an project that aims to create an day view (calendar) that is light weight, pure java/javafx, easy to use and **free**!



It can display an day calendar in minute, hour and day view whit the abilities to handle multiple events on the same time. It can also detect marking of minutes in the minute column. 

Some coming features:
* easy mount event listener
* update the event ui to a bitt more "fancy"
* Make it possible to "on the go" change the layout day/hour/minute...
* Make it possibly to dynamic change the minute size (low priority for the moment) 


## How to ouse
Add the Jar file to youre prodjekt or compile the classes:
```
/DayViewJavaFX/src/dayGrapics/CalenderBuilderContainer.java
/DayViewJavaFX/src/dayGrapics/CalenderEvent.java
/DayViewJavaFX/src/dayGrapics/TidPunkt.java
```

Then in your JavaFx application add an gridPane (**that is empty**) and add it to the CalenderBuilderContainer constructor and then it is ready to use. And you can create CalenderEvent events and add to the CalenderBuilderContainer that will sort the rest out :)

#### Example how it can look for the minute view:
<img src="https://github.com/flaime/DayViewJavaFX/blob/master/readmeFiles/dayView.PNG" width="300">
#### Example how it can look for the hour view:
<img src="https://github.com/flaime/DayViewJavaFX/blob/master/readmeFiles/dayView%20timmar.PNG" width="300">
#### Example how it can look for the day view:
<img src="https://github.com/flaime/DayViewJavaFX/blob/master/readmeFiles/dayView%20day.PNG" width="300">
