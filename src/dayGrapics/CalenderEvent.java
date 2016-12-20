package dayGrapics;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;

public class CalenderEvent {

	private TidPunkt från;
	private TidPunkt till;
	private SimpleStringProperty rubrik = new SimpleStringProperty();
	private SimpleStringProperty boddy = new SimpleStringProperty();
	private boolean scrollEnabled = false;
	private static int nextId = 1;
	private final int dennasId;
	private SimpleStringProperty FrånTillObservably = new SimpleStringProperty();
	
	public CalenderEvent(TidPunkt från, TidPunkt till, String rubrik, String boddy, boolean scrollEnabled) {
		this.boddy.setValue(boddy);
		this.rubrik.setValue(rubrik);
		this.från = från;
		this.till = till;
		this.scrollEnabled = scrollEnabled;
		FrånTillObservably.setValue(getFrån()+"-"+getTill());
		dennasId = nextId++;
		
	}
	public CalenderEvent(TidPunkt från, TidPunkt till, String rubrik, String boddy) {
		this.boddy.setValue(boddy);
		this.rubrik.setValue(rubrik);
		this.från = från;
		this.till = till;
		dennasId = nextId++;
		FrånTillObservably.setValue(getFrån()+"-"+getTill());
		
	}
	public TidPunkt getFrån() {
		return från;
	}
	public void setFrån(TidPunkt från) {
		this.från = från;
		FrånTillObservably.setValue(getFrån()+"-"+getTill());
	}
	public TidPunkt getTill() {
		return till;
	}
	public void setTill(TidPunkt till) {
		this.till = till;
		FrånTillObservably.setValue(getFrån()+"-"+getTill());
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
