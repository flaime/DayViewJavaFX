package dayGrapics;

import javafx.beans.property.SimpleStringProperty;

public class TidPunkt {
	private int minut;
	private int timme;
	private SimpleStringProperty tiden = new SimpleStringProperty();
	public TidPunkt(int timme, int minut) {
		this.timme = timme;
		this.minut = minut;
		tiden.setValue(formaterarTimmeOchMinut(timme)+":"+formaterarTimmeOchMinut(minut));
	}
	public int getTimme() {
		return timme;
	}
	
	public int getMinut() {
		return minut;
	}
	public SimpleStringProperty gettidenBinding(){
		return tiden;
	}
	@Override
	public String toString() {
		return formaterarTimmeOchMinut(timme)+":"+formaterarTimmeOchMinut(minut);
	}
	
	private String formaterarTimmeOchMinut(int minut){
		if((minut+"").length()==1)
			return "0"+minut;
		else
			return minut+"";
	}
}
