package dayGrapics;

import java.util.ArrayList;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;

public class CalenderEvent {

	private TidPunkt från;
	private TidPunkt till;
	private SimpleStringProperty rubrik = new SimpleStringProperty();
	private SimpleStringProperty boddy = new SimpleStringProperty();
	private boolean scrollEnabled = false;
	private static int nextId = 1;
	private final int dennasId;
	private SimpleStringProperty FrånTillObservably = new SimpleStringProperty();
	private ArrayList<CalenderBuilderContainer> uppdateTimeLinks = new ArrayList<>();
	
	public CalenderEvent(TidPunkt från, TidPunkt till, String rubrik, String boddy, boolean scrollEnabled) {
		this.boddy.setValue(boddy);
		this.rubrik.setValue(rubrik);
		this.från = från;
		this.till = till;
		this.scrollEnabled = scrollEnabled;
		FrånTillObservably.set(getFrån()+"-"+getTill());
//		FrånTillObservably.bind(Bindings.concat(från.gettidenBinding(), "-",till.gettidenBinding()));// från.gettidenBinding(),till.gettidenBinding());// setValue(getFrån()+"-"+getTill());
		dennasId = nextId++;
		
	}
	public CalenderEvent(TidPunkt från, TidPunkt till, String rubrik, String boddy) {
		this.boddy.setValue(boddy);
		this.rubrik.setValue(rubrik);
		this.från = från;
		this.till = till;
		dennasId = nextId++;
		FrånTillObservably.set(getFrån()+"-"+getTill());
		
	}
	public TidPunkt getFrån() {
		return från;
	}

	public void setNyTid(TidPunkt från,TidPunkt till) {
		this.från = från;
		this.till = till;
		CalenderEvent denna = this;
		Platform.runLater(new Runnable() {
	        @Override
	        public void run() {
	        	FrånTillObservably.set(getFrån()+"-"+getTill());
	        	for(CalenderBuilderContainer b : uppdateTimeLinks)
	        		b.uppdateCalenderEventTime(denna);
	        }
	      });
		
		
	}
	public void setFrån(TidPunkt från) {
		this.från = från;
		CalenderEvent denna = this;
		Platform.runLater(new Runnable() {
	        @Override
	        public void run() {
	        	FrånTillObservably.set(getFrån()+"-"+getTill());
	        	for(CalenderBuilderContainer b : uppdateTimeLinks)
	        		b.uppdateCalenderEventTime(denna);
	        }
	      });
		
		
	}
	public TidPunkt getTill() {
		return till;
	}
	public void setTill(TidPunkt till) {
		this.till = till;
		CalenderEvent denna = this;
		Platform.runLater(new Runnable() {
	        @Override
	        public void run() {
	        	FrånTillObservably.setValue(getFrån()+"-"+getTill());
	        	for(CalenderBuilderContainer b : uppdateTimeLinks)
	        		b.uppdateCalenderEventTime(denna);
	        }
	      });
		
		uppdateTimeLinks.forEach(x-> x.uppdateCalenderEventTime(this));
	}
	public String getRubrik() {
		return rubrik.getValue();
	}
	public void setRubrik(String rubrik) {
		this.rubrik.setValue(rubrik);
	}
	public String getBoddy() {
		return boddy.getValue();
	}
	public void setBoddy(String boddy) {
		this.boddy.setValue(boddy);
	}//medvetet satt utan modifierare på de nedan.....!
	SimpleStringProperty getFrånTillObservably() {
		return FrånTillObservably;
	}
	SimpleStringProperty getBoddyObservably() {
		return boddy;
	}
	SimpleStringProperty getRubrikObservably() {
		return rubrik;
	}
	boolean addRefrense(CalenderBuilderContainer cbc){
		return uppdateTimeLinks.add(cbc);
	}
	boolean removeRefrence(CalenderBuilderContainer cbc){
		return uppdateTimeLinks.remove(cbc);
	}
	@Override
	public String toString() {
		return "CalenderEvent [från=" + från + ", till=" + till + ", rubrik=" + rubrik + ", boddy=" + boddy + "]";
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
