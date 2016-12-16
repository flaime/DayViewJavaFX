package dayGrapics;

public class TidPunkt {
	private int minut;
	private int timme;
	public TidPunkt(int timme, int minut) {
		this.timme = timme;
		this.minut = minut;
	}
	public int getTimme() {
		return timme;
	}
	
	public int getMinut() {
		return minut;
	}
	
	@Override
	public String toString() {
		return fixaTillMinuter(timme)+":"+fixaTillMinuter(minut);
	}
	
	private String fixaTillMinuter(int minut){
		if((minut+"").length()==1)
			return "0"+minut;
		else
			return minut+"";
	}
}
