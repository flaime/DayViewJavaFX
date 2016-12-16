package dayGrapics;

public class CalenderEvent {

	TidPunkt fr�n;
	TidPunkt till;
	String rubrik;
	String boddy;
	private static int nextId = 1;
	private final int dennasId;
	
	public CalenderEvent(TidPunkt fr�n, TidPunkt till, String rubrik, String boddy) {
		this.boddy = boddy;
		this.rubrik = rubrik;
		this.fr�n = fr�n;
		this.till = till;
		dennasId = nextId++;
		
	}
	public TidPunkt getFr�n() {
		return fr�n;
	}
	public void setFr�n(TidPunkt fr�n) {
		this.fr�n = fr�n;
	}
	public TidPunkt getTill() {
		return till;
	}
	public void setTill(TidPunkt till) {
		this.till = till;
	}
	public String getRubrik() {
		return rubrik;
	}
	public void setRubrik(String rubrik) {
		this.rubrik = rubrik;
	}
	public String getBoddy() {
		return boddy;
	}
	public void setBoddy(String boddy) {
		this.boddy = boddy;
	}
	@Override
	public String toString() {
		return "CalenderEvent [fr�n=" + fr�n + ", till=" + till + ", rubrik=" + rubrik + ", boddy=" + boddy + "]";
	}
	public int getId() {
		return dennasId;
	}
	
}
