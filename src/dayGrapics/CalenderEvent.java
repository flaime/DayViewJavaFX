package dayGrapics;

public class CalenderEvent {

	TidPunkt från;
	TidPunkt till;
	String rubrik;
	String boddy;
	private boolean scrollEnabled = true;
	private static int nextId = 1;
	private final int dennasId;
	
	public CalenderEvent(TidPunkt från, TidPunkt till, String rubrik, String boddy, boolean scrollEnabled) {
		this.boddy = boddy;
		this.rubrik = rubrik;
		this.från = från;
		this.till = till;
		this.scrollEnabled = scrollEnabled;
		dennasId = nextId++;
		
	}
	public TidPunkt getFrån() {
		return från;
	}
	public void setFrån(TidPunkt från) {
		this.från = från;
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
	
}
