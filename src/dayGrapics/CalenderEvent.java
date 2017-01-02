package dayGrapics;

import java.util.ArrayList;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class CalenderEvent {

	private Time from;
	private Time to;
	private SimpleStringProperty heading = new SimpleStringProperty();
	private SimpleStringProperty boddy = new SimpleStringProperty();
	private boolean scrollEnabled = false;
	private static int nextId = 1;
	private final int dennasId;
	private SimpleStringProperty fromtoObservably = new SimpleStringProperty();
	private ArrayList<CalenderBuilderContainer> uppdateTimeLinks = new ArrayList<>();
	private Color colorBody = null;
	private Color colorRibrik = null;
	
	public CalenderEvent(Time from, Time to, String heading, String boddy,Color boddyColor, Color headingColor) {
		this(from, to, heading, boddy);
		colorBody = boddyColor;
		colorRibrik = headingColor;
	}
	public CalenderEvent(Time from, Time to, String heading, String boddy, boolean scrollEnabled,Color boddyColor, Color headingColor) {
		this(from, to, heading, boddy, scrollEnabled);
		colorBody = boddyColor;
		colorRibrik = headingColor;
	}
	public CalenderEvent(Time from, Time to, String heading, String boddy, boolean scrollEnabled) {
		this.boddy.setValue(boddy);
		this.heading.setValue(heading);
		this.from = from;
		this.to = to;
		this.scrollEnabled = scrollEnabled;
		fromtoObservably.set(getFrom()+"-"+getTo());
//		fromtoObservably.bind(Bindings.concat(from.gettidenBinding(), "-",to.gettidenBinding()));// from.gettidenBinding(),to.gettidenBinding());// setValue(getfrom()+"-"+getto());
		dennasId = nextId++;
		
	}
	public CalenderEvent(Time from, Time to, String heading, String boddy) {
		this.boddy.setValue(boddy);
		this.heading.setValue(heading);
		this.from = from;
		this.to = to;
		dennasId = nextId++;
		fromtoObservably.set(getFrom()+"-"+getTo());
		
	}
	public Time getFrom() {
		return from;
	}

	public void setNewTime(Time from,Time to) {
		this.from = from;
		this.to = to;
		CalenderEvent denna = this;
		Platform.runLater(new Runnable() {
	        @Override
	        public void run() {
	        	fromtoObservably.set(getFrom()+"-"+getTo());
	        	for(CalenderBuilderContainer b : uppdateTimeLinks)
	        		b.uppdateCalenderEventTime(denna);
	        }
	      });
		
		
	}
	public void setFrom(Time from) {
		this.from = from;
		CalenderEvent denna = this;
		Platform.runLater(new Runnable() {
	        @Override
	        public void run() {
	        	fromtoObservably.set(getFrom()+"-"+getTo());
	        	for(CalenderBuilderContainer b : uppdateTimeLinks)
	        		b.uppdateCalenderEventTime(denna);
	        }
	      });
		
		
	}
	public Time getTo() {
		return to;
	}
	public void setTo(Time to) {
		this.to = to;
		CalenderEvent denna = this;
		Platform.runLater(new Runnable() {
	        @Override
	        public void run() {
	        	fromtoObservably.setValue(getFrom()+"-"+getTo());
	        	for(CalenderBuilderContainer b : uppdateTimeLinks)
	        		b.uppdateCalenderEventTime(denna);
	        }
	      });
		
		uppdateTimeLinks.forEach(x-> x.uppdateCalenderEventTime(this));
	}
	public String getHeading() {
		return heading.getValue();
	}
	public void setheading(String heading) {
		this.heading.setValue(heading);
	}
	public String getBoddy() {
		return boddy.getValue();
	}
	public void setBoddy(String boddy) {
		this.boddy.setValue(boddy);
	}//medvetet satt utan modifierare på de nedan.....!
	SimpleStringProperty getfromToObservably() {
		return fromtoObservably;
	}
	SimpleStringProperty getBoddyObservably() {
		return boddy;
	}
	SimpleStringProperty getheadingObservably() {
		return heading;
	}
	boolean addRefrense(CalenderBuilderContainer cbc){
		return uppdateTimeLinks.add(cbc);
	}
	boolean removeRefrence(CalenderBuilderContainer cbc){
		return uppdateTimeLinks.remove(cbc);
	}
	@Override
	public String toString() {
		return "CalenderEvent [from=" + from + ", to=" + to + ", heading=" + heading + ", boddy=" + boddy + "]";
	}
	public int getId() {
		return dennasId;
	}
	public boolean isScrollEnabled() {
		return scrollEnabled;
	}
	public void setScrollEnabled(boolean scrollEnabled) {
		this.scrollEnabled = scrollEnabled;
	}
	public Color getColorHeading() {
		return colorRibrik;
	}
	public void setColorHeading(Color colorRibrik) {
		this.colorRibrik = colorRibrik;
		uppdateTimeLinks.forEach(x-> x.uppdateCalenderEventColor(this));
	}
	
	public Color getColorBody() {
		return colorBody;
	}
	public void setColorBody(Color colorBody) {
		this.colorBody = colorBody;
		uppdateTimeLinks.forEach(x-> x.uppdateCalenderEventColor(this));
	}
	public void setColor(Color color) {
		this.colorBody = color;
		this.colorRibrik = color;
		uppdateTimeLinks.forEach(x-> x.uppdateCalenderEventColor(this));
	}
	public void setColor(Color colorBody, Color colorHedder) {
		this.colorBody = colorBody;
		this.colorRibrik = colorHedder;
		uppdateTimeLinks.forEach(x-> x.uppdateCalenderEventColor(this));
	}
	@Override
	public boolean equals(Object obj) {
		if((obj == null) || (obj.getClass() != this.getClass())) {
			return false;
		}else if(((CalenderEvent) obj).getId() == getId())
			return true;

		
		return false;
	}
	
	@Override
	public int hashCode() {
		return getId();
	}
}
