package dayGrapics;

import javafx.beans.property.SimpleStringProperty;

public class Time {
	private int minut;
	private int hour;
	private SimpleStringProperty tiden = new SimpleStringProperty();
	public Time(int hour, int minut) {
		this.hour = hour;
		this.minut = minut;
		tiden.setValue(formaterarTimmeOchMinut(hour)+":"+formaterarTimmeOchMinut(minut));
	}
	public int getHour() {
		return hour;
	}
	
	public int getMinut() {
		return minut;
	}
	public SimpleStringProperty gettidenBinding(){
		return tiden;
	}
	@Override
	public String toString() {
		return formaterarTimmeOchMinut(hour)+":"+formaterarTimmeOchMinut(minut);
	}
	
	private String formaterarTimmeOchMinut(int minut){
		if((minut+"").length()==1)
			return "0"+minut;
		else
			return minut+"";
	}
	
	@Override
	public int hashCode() {
		return toString().hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null)
			return false;
		else if(obj instanceof Time){
			if(((Time)obj).getMinut() == getMinut() && ((Time)obj).getHour()==getHour())
				return true;
		}
		
		return false;
	}
}
