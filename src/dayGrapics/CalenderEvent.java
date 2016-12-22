package dayGrapics;

import java.util.ArrayList;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;

public class CalenderEvent {

	private TidPunkt fr�n;
	private TidPunkt till;
	private SimpleStringProperty rubrik = new SimpleStringProperty();
	private SimpleStringProperty boddy = new SimpleStringProperty();
	private boolean scrollEnabled = false;
	private static int nextId = 1;
	private final int dennasId;
	private SimpleStringProperty Fr�nTillObservably = new SimpleStringProperty();
	private ArrayList<CalenderBuilderContainer> uppdateTimeLinks = new ArrayList<>();
	
	public CalenderEvent(TidPunkt fr�n, TidPunkt till, String rubrik, String boddy, boolean scrollEnabled) {
		this.boddy.setValue(boddy);
		this.rubrik.setValue(rubrik);
		this.fr�n = fr�n;
		this.till = till;
		this.scrollEnabled = scrollEnabled;
		Fr�nTillObservably.set(getFr�n()+"-"+getTill());
//		Fr�nTillObservably.bind(Bindings.concat(fr�n.gettidenBinding(), "-",till.gettidenBinding()));// fr�n.gettidenBinding(),till.gettidenBinding());// setValue(getFr�n()+"-"+getTill());
		dennasId = nextId++;
		
	}
	public CalenderEvent(TidPunkt fr�n, TidPunkt till, String rubrik, String boddy) {
		this.boddy.setValue(boddy);
		this.rubrik.setValue(rubrik);
		this.fr�n = fr�n;
		this.till = till;
		dennasId = nextId++;
		Fr�nTillObservably.set(getFr�n()+"-"+getTill());
		
	}
	public TidPunkt getFr�n() {
		return fr�n;
	}

	public void setNyTid(TidPunkt fr�n,TidPunkt till) {
		this.fr�n = fr�n;
		this.till = till;
		CalenderEvent denna = this;
		Platform.runLater(new Runnable() {
	        @Override
	        public void run() {
	        	Fr�nTillObservably.set(getFr�n()+"-"+getTill());
	        	for(CalenderBuilderContainer b : uppdateTimeLinks)
	        		b.uppdateCalenderEventTime(denna);
	        }
	      });
		
		
	}
	public void setFr�n(TidPunkt fr�n) {
		this.fr�n = fr�n;
		CalenderEvent denna = this;
		Platform.runLater(new Runnable() {
	        @Override
	        public void run() {
	        	Fr�nTillObservably.set(getFr�n()+"-"+getTill());
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
	        	Fr�nTillObservably.setValue(getFr�n()+"-"+getTill());
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
	}//medvetet satt utan modifierare p� de nedan.....!
	SimpleStringProperty getFr�nTillObservably() {
		return Fr�nTillObservably;
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
		return "CalenderEvent [fr�n=" + fr�n + ", till=" + till + ", rubrik=" + rubrik + ", boddy=" + boddy + "]";
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
