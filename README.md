# DayViewJavaFX
DayViewJavaFX is an project that aims to create an day view (calendar) that is light weight, pure java/javafx, easy to use and **free**!



It can display an day calendar in minute, hour and day view whit the abilities to handle multiple events on the same time. It can also detect marking of minutes. 

Fetures (the bigest)
* easy add/remove element
* easy change event (time, header, color and so on)
* set multiple colors for event
* mark time periods
* programmatically set/get marked time
* chek if time overlap


Some coming features:
- [ ] Easy mount event listener
- [ ] Update the event ui to a bitt more "fancy" (som parts don)
- [ ] Make it possible to "on the go" change the layout day/hour/minute...
- [x] **done** Easier auto update of values like body text, time....
- [ ] Make it possibly to dynamic change the minute size (low priority for the moment)
- [x] **mostly done** (maybe an rewrite sometime thug)Rewrite the whole or solve the "bugs" that makes it extremely slow (**priority**) not as slow
- [ ] **mostly don**Change alla swedish to english at least the methods and "TidPunkt" class
- [ ] Add validation on corect time and so on...


## How to ouse
Add the [Jar](https://github.com/flaime/DayViewJavaFX/releases) file to youre prodjekt or compile the classes:
```
/DayViewJavaFX/src/dayGrapics/CalenderBuilderContainer.java
/DayViewJavaFX/src/dayGrapics/CalenderEvent.java
/DayViewJavaFX/src/dayGrapics/TidPunkt.java
```

Then in your JavaFx application add an gridPane (**that is empty**) and add it to the CalenderBuilderContainer constructor and then it is ready to use. And you can create CalenderEvent events and add to the CalenderBuilderContainer that will sort the rest out :)

Example below:
```
GridPane  gridPane = new GridPane();
//adding the gridPane to the XML is missing...
CalenderBuilderContainer cbc = new CalenderBuilderContainer(gridPane, Vy.dagVy);

CalenderEvent ce1 =  new CalenderEvent(new TidPunkt(9, 15), new TidPunkt(10, 00), "heading here", "The body text here", false); //where the false is optional and stands for scrollable
cbc.addEvent(ce1);
```
And by that we have been creating a calendar with one calendar event that is between 9:15-10:00

#### Example how it can look for the minute view:
<img src="https://github.com/flaime/DayViewJavaFX/blob/master/readmeFiles/dayView.PNG" width="300">

#### Example how it can look for the hour view:
<img src="https://github.com/flaime/DayViewJavaFX/blob/master/readmeFiles/dayView%20timmar.PNG" width="300">
#### Example how it can look for the day view:
<img src="https://github.com/flaime/DayViewJavaFX/blob/master/readmeFiles/dayView%20day.PNG" width="300">
