package dayGrapics;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;

public class CalenderEvent {

	private TidPunkt fr�n;
	private TidPunkt till;
	private SimpleStringProperty rubrik = new SimpleStringProperty();
	private SimpleStringProperty boddy = new SimpleStringProperty();
	private boolean scrollEnabled = false;
	private static int nextId = 1;
	private final int dennasId;
	private SimpleStringProperty Fr�nTillObservably = new SimpleStringProperty();
	
	public CalenderEvent(TidPunkt fr�n, TidPunkt till, String rubrik, String boddy, boolean scrollEnabled) {
		this.boddy.setValue(boddy);
		this.rubrik.setValue(rubrik);
		this.fr�n = fr�n;
		this.till = till;
		this.scrollEnabled = scrollEnabled;
		Fr�nTillObservably.setValue(getFr�n()+"-"+getTill());
		dennasId = nextId++;
		
	}
	public CalenderEvent(TidPunkt fr�n, TidPunkt till, String rubrik, String boddy) {
		this.boddy.setValue(boddy);
		this.rubrik.setValue(rubrik);
		this.fr�n = fr�n;
		this.till = till;
		dennasId = nextId++;
		Fr�nTillObservably.setValue(getFr�n()+"-"+getTill());
		
	}
	public TidPunkt getFr�n() {
		return fr�n;
	}
	public void setFr�n(TidPunkt fr�n) {
		this.fr�n = fr�n;
		Fr�nTillObservably.setValue(getFr�n()+"-"+getTill());
	}
	public TidPunkt getTill() {
		return till;
	}
	public void setTill(TidPunkt till) {
		this.till = till;
		Fr�nTillObservably.setValue(getFr�n()+"-"+getTill());
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
