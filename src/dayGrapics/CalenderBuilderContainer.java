package dayGrapics;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Time;
import java.util.ArrayList;
import java.util.function.Predicate;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CalenderBuilderContainer{

	@FXML
	private GridPane gridPane;

	public enum Vy {
		timmVy, minutVy, dagVy
	}
	
	private void setVy(Vy vy){
		vyn = vy;
	}
	private ArrayList<ArrayList<CalenderEvent>> calenderHändelser = new ArrayList<>();
	
//	private KalenderInehållFast 

	private Vy vyn = Vy.minutVy;
//	Vy vyn = Vy.timmVy;
//	Vy vyn = Vy.dagVy;
	
	private double minutHöjd = -1;
	
	
	private ArrayList<Text> allaMinuter = new ArrayList<>();
	
	// final Text source = new Text(50, 100, "DRAG ME");
	// final Text target = new Text(300, 100, "DROP HERE");

	
	public CalenderBuilderContainer(GridPane grid) {
		gridPane = grid;
		
		gridPane.getChildren().clear();//ska förhopnningsvis tömma gripanen helt så man kan börja med en "ren"
		
		Method method;
		Integer rows = new Integer(-111);
		try {
			method = gridPane.getClass().getDeclaredMethod("getNumberOfRows");
			method.setAccessible(true);
			rows = (Integer) method.invoke(gridPane);
		} catch (NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

//		GridPane gridpane = new GridPane();
		
	     
	     
		ritaGrundKalender();
		calenderHändelser.add(new ArrayList<CalenderEvent>());

		for (int i = 0; i < 1440; i++) {
	         RowConstraints row = new RowConstraints(minutHöjd);
	         gridPane.getRowConstraints().add(row);
	     }
		
		addEvent(new TidPunkt(0, 30), new TidPunkt(0, 35), "Veckans försla lopp", "ja en heäldese men som inte har så mycket texti sig\nadsfa\nasdfaf\nasdfa\nhej\nkalas");
		addEvent(new TidPunkt(0, 32), new TidPunkt(0, 37), "Veckans försla lopp", "ja en heäldese men som inte har så mycket texti sig\nadsfa\nasdfaf\nasdfa\nhej\nkalas");
		addEvent(new TidPunkt(0, 35), new TidPunkt(0, 40), "Veckans försla lopp", "ja en heäldese men som inte har så mycket texti sig\nadsfa\nasdfaf\nasdfa\nhej\nkalas");
		addEvent(new TidPunkt(0, 37), new TidPunkt(0, 42), "Veckans försla lopp", "ja en heäldese men som inte har så mycket texti sig\nadsfa\nasdfaf\nasdfa\nhej\nkalas");
		addEvent(new TidPunkt(0, 39), new TidPunkt(0, 50), "Veckans försla lopp", "ja en heäldese men som inte har så mycket texti sig\nadsfa\nasdfaf\nasdfa\nhej\nkalas");
		addEvent(new TidPunkt(0, 40), new TidPunkt(0, 53), "Veckans försla lopp", "ja en heäldese men som inte har så mycket texti sig\nadsfa\nasdfaf\nasdfa\nhej\nkalas");
		addEvent(new TidPunkt(0, 41), new TidPunkt(0, 55), "Veckans försla lopp", "ja en heäldese men som inte har så mycket texti sig\nadsfa\nasdfaf\nasdfa\nhej\nkalas");
		addEvent(new TidPunkt(0, 42), new TidPunkt(1, 05), "Veckans försla lopp", "ja en heäldese men som inte har så mycket texti sig\nadsfa\nasdfaf\nasdfa\nhej\nkalas");
//		addEvent(new TidPunkt(0, 35), new TidPunkt(0, 45), "dagens andra lopp ", "ja en heäldese men som inte har så mycket texti sig");
//		addEvent(new TidPunkt(0, 35), new TidPunkt(0, 45), "dagens andra lopp ", "ja en heäldese men som inte har så mycket texti sig");
//		addEvent(new TidPunkt(0, 37), new TidPunkt(0, 56), "Lopp nummer tre för dagen", "ja en heäldese men som inte har så mycket texti sig");
//		addEvent(new TidPunkt(0, 56), new TidPunkt(1, 15), "ja ett lopp", "ja en heäldese men som inte har så mycket texti sig");
//		addEvent(new TidPunkt(1, 15), new TidPunkt(1, 30), "en händelse", "ja en heäldese men som inte har så mycket texti sig");
//		addEvent(new TidPunkt(1, 32), new TidPunkt(2, 10), "ja snart lunch?", "ja en heäldese men som inte har så mycket texti sig");
//		addEvent(new TidPunkt(2, 10), new TidPunkt(2, 30), "in lunch än?", "ja en heäldese men som inte har så mycket texti sig");
//		addEvent(new TidPunkt(2, 30), new TidPunkt(3, 00), "mellanmål mums", "ja en heäldese men som inte har så mycket texti sig");
//		addEvent(new TidPunkt(3, 31), new TidPunkt(3, 32), "mellanmål mums", "ja en heäldese men som inte har så mycket texti sig");
	
	}

	
//	private void initialize() {
//		
//
//		// sätter dragblörjan saker
//		source.setOnDragDetected(new EventHandler<MouseEvent>() {
//			public void handle(MouseEvent event) {
//				/* drag was detected, start a drag-and-drop gesture */
//				/* allow any transfer mode */
//				Dragboard db = source.startDragAndDrop(TransferMode.ANY);
//
//				/* Put a string on a dragboard */
//				ClipboardContent content = new ClipboardContent();
//				content.putString(source.getText());
//				db.setContent(content);
//
//				event.consume();
//			}
//		});
//
//		// saker för det som det ska kopieras til
//		target.setOnDragOver(new EventHandler<DragEvent>() {
//			public void handle(DragEvent event) {
//				/* data is dragged over the target */
//				/*
//				 * accept it only if it is not dragged from the same node and if
//				 * it has a string data
//				 */
//				if (event.getGestureSource() != target && event.getDragboard().hasString()) {
//					/*
//					 * allow for both copying and moving, whatever user chooses
//					 */
//					event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
//				}
//
//				event.consume();
//			}
//		});
//
//		target.setOnDragEntered(new EventHandler<DragEvent>() {
//			public void handle(DragEvent event) {
//				/* the drag-and-drop gesture entered the target */
//				/* show to the user that it is an actual gesture target */
//				if (event.getGestureSource() != target && event.getDragboard().hasString()) {
//					target.setFill(Color.GREEN);
//				}
//
//				event.consume();
//			}
//		});
//
//		target.setOnDragExited(new EventHandler<DragEvent>() {
//			public void handle(DragEvent event) {
//				/* mouse moved away, remove the graphical cues */
//				target.setFill(Color.BLACK);
//
//				event.consume();
//			}
//		});
//
//		target.setOnDragDropped(new EventHandler<DragEvent>() {
//			public void handle(DragEvent event) {
//				/* data dropped */
//				/* if there is a string data on dragboard, read it and use it */
//				Dragboard db = event.getDragboard();
//				boolean success = false;
//				if (db.hasString()) {
//					target.setText(db.getString());
//					success = true;
//				}
//				/*
//				 * let the source know whether the string was successfully
//				 * transferred and used
//				 */
//				event.setDropCompleted(success);
//
//				event.consume();
//			}
//		});
//
//		source.setOnDragDone(new EventHandler<DragEvent>() {
//			public void handle(DragEvent event) {
//				/* the drag and drop gesture ended */
//				/* if the data was successfully moved, clear it */
//				if (event.getTransferMode() == TransferMode.MOVE) {
//					source.setText("");
//				}
//				event.consume();
//			}
//		});
//
//		
//		
//		
//		
//		
//	}

	private BorderPane rootLayout;
	private Stage primaryStage;

	private void ritaGrundKalender() {
		/**
		 * Här ställer man in hur stora texter och hur stora som olika minuterna ska vara i de tre olika typerna av vyer
		 * 
		 * det att tänka på att tim och dagsvyn minutstorlekln kan ändras direkt men att minutvyn måste. Ska man ändra storleken på texten för att ändra hur stora minuterna där ska vara.
		 */
		double timVyMinutHeight = 3;
		Font minutVyFont = Font.font("Verdana", 7);
		//bortse från det kommande 4 raderna nedan bara för att räkna ut hur höga var minut kommer bli
		Text tillHöjdBeräkning = new Text();
		tillHöjdBeräkning.setText("12");
		tillHöjdBeräkning.setFont(minutVyFont);
		tillHöjdBeräkning.applyCss(); 
		double minutVyMinutHeight = tillHöjdBeräkning.getLayoutBounds().getHeight();
		double dagVyMinutHeight = 1;
		
			int minut = 0;
			int timme = 0;
			for (int i = 0; i < 1440; i++) { //1380 //1440
				if (minut == 60 || minut == 0) {
					
					minut = 0;
					Text t = new Text();

					t.setText(timme++ + "");
					t.setFont(Font.font("Verdana", 20));
					t.setFill(Color.RED);
					t.applyCss();
					
					int heightInMinutes = -1;
					switch (vyn) {
					case minutVy:
						heightInMinutes = (int) Math.ceil(t.getLayoutBounds().getHeight()/minutVyMinutHeight);
						break;
					case timmVy:
						heightInMinutes = (int) Math.ceil(t.getLayoutBounds().getHeight()/timVyMinutHeight);
						break;
					case dagVy:
						heightInMinutes = (int) Math.ceil(t.getLayoutBounds().getHeight()/dagVyMinutHeight);
						break;
					default:
						break;
					}
					  
					if (i != 0)
						gridPane.add(t, 1, i - 1, 1, heightInMinutes);
					else
						gridPane.add(t, 1, i, 1, heightInMinutes);
				}
				
				if (vyn == Vy.minutVy) {
					Text t = new Text();
					t.setText(fixaTillMinuter(minut++));
					t.setFont(minutVyFont);
					t.applyCss(); 
				    minutHöjd = Math.ceil(t.getLayoutBounds().getHeight());
				    
				    t = addMinutTextEgenskaper(t, minut -1,timme-1);
					gridPane.add(t, 2, i);
					
//					gridPane.add(child, 3, i);
				}else if(vyn == Vy.timmVy){
					Text t = new Text();
					t.setText(fixaTillMinuter(minut++));
					t.setFont(Font.font("Verdana", 12));
					t.applyCss(); 
					t = addMinutTextEgenskaper(t, minut -1,timme-1);
					
				    
					if(t.getText().matches("\\d0")){
						int heightInMinutes =  (int) Math.ceil(t.getLayoutBounds().getHeight()/timVyMinutHeight);
						gridPane.add(t, 2, i, 1, heightInMinutes);
					}else{
						Pane p = new Pane();
						p.setPrefHeight(timVyMinutHeight);
						gridPane.add(p, 2, i);
					}
					minutHöjd = Math.ceil(timVyMinutHeight);
				}else if(vyn == Vy.dagVy){
					Text t = new Text();
					t.setText(fixaTillMinuter(minut++));
					t.setFont(Font.font("Verdana", 12));
					t.applyCss();
					t = addMinutTextEgenskaper(t, minut -1,timme-1);
					
				    minutHöjd = Math.ceil(dagVyMinutHeight);// t.getLayoutBounds().getHeight();
				    int heightInMinutes =  (int) Math.ceil(t.getLayoutBounds().getHeight()/dagVyMinutHeight);
					if(t.getText().matches("30") || t.getText().matches("00")){
						gridPane.add(t, 2, i, 1, heightInMinutes);
					}else{
						Pane p = new Pane();
						p.setPrefHeight(dagVyMinutHeight);
						gridPane.add(p, 2, i);
					}
					
				}
			}
	}
	

	private String fixaTillMinuter(int minut){
		if((minut+"").length()==1)
			return "0"+minut;
		else
			return minut+"";
	}
	public static ArrayList<ScrollPane> retList = new ArrayList<>();
	
	
	public boolean addEvent(TidPunkt från, TidPunkt till, String rubrik, String boddy){
		CalenderEvent event = new CalenderEvent(från, till, rubrik, boddy);
		return addEvent(event);
	}
	public boolean addEvent(CalenderEvent event){		
		//lite fakta om var den ska sitta någonstans
		int startRuta = event.getFrån().getTimme()*60;
		startRuta += event.getFrån().getMinut();
		int slutRuta = event.getTill().getTimme()*60;
		slutRuta += event.getTill().getMinut();
		int antalRuterTillSlut = slutRuta - startRuta;
		double maxTillåtnaHeight = minutHöjd*antalRuterTillSlut;
		
//		if(finnsEventPåTiden(från,till)==false)
		
		int nivå = vilkenNivåSkaEvnetLäggasPå(event);
		tittaOmNivåFinnsOmInteLäggTill(nivå);
		calenderHändelser.get(nivå).add(event);//för  att kunna tillta var de finns till senare och ta ut hämta ut händelser
		ScrollPane ret = createCalenderEvent(event, maxTillåtnaHeight);
		gridPane.add(ret, 3+nivå, startRuta, 1, antalRuterTillSlut);
		retList.add(ret);
		
		return true;
	}
	

	private ScrollPane createCalenderEvent(CalenderEvent event,double height){ //String rubriken, String boddy, double height){
		
		Text rubrik = new Text();
		rubrik.setText(event.getFrån() + "-" + event.getTill() + "\t" +event.getRubrik());
		StackPane rubrikPane = new StackPane(rubrik);
		rubrikPane.setBackground(new Background(new BackgroundFill(Color.BEIGE, CornerRadii.EMPTY, Insets.EMPTY)));
		
		Text bodyn = new Text();
		bodyn.setText(event.getBoddy());
		
		Separator sep = new Separator();
		GridPane kroppen = new GridPane();
		
		kroppen.add(rubrikPane, 1, 1);
		kroppen.add(bodyn, 1, 3);
		kroppen.add(sep, 1,2);
		
		ScrollPane ret = new ScrollPane(kroppen);
		ret.setMaxHeight(height);
		ret.setPrefHeight(height);
		ret.setMinHeight(height);
		
		ret.setOnMouseClicked(new EventHandler<MouseEvent>(){
			 
	          @Override
	          public void handle(MouseEvent arg0) {
	             
	        	  Alert alert = new Alert(AlertType.INFORMATION);
	        	  alert.setTitle("Calender händelse");
	        	  alert.setHeaderText(event.getFrån() + "-" + event.getTill() + " " +event.getRubrik());
	        	  alert.setContentText("bodyn:\n" +event.getBoddy());

	        	  alert.showAndWait();
	          }
	 
	      });
		
		ret.setId(event.getId()+"");
	    return ret;
		
	}

	
	private int vilkenNivåSkaEvnetLäggasPå(CalenderEvent event){
		//eventen räknas 0 som längst till vänster och sedan högre för varje kolumen till höger
		int nivå =0;
		for(ArrayList<CalenderEvent> array: calenderHändelser){
			int nivåinnan = nivå;
			for(CalenderEvent testaMot: array){
				if(överlapparDessa(testaMot, event)){
					nivå++;
					break;
				}
				
			}
			if(nivå == nivåinnan)
				break;
		}
		return nivå;
	}
	
	private void tittaOmNivåFinnsOmInteLäggTill(int nivå){
		if(calenderHändelser.size() >= nivå+1){
			
		}else{
			for(int i =0; i < (nivå -calenderHändelser.size()+1);i++){
				calenderHändelser.add(new ArrayList<CalenderEvent>());
			}
		}
	}
	
	
	public static boolean överlapparDessa(CalenderEvent a, CalenderEvent b){
		int aStart = (a.getFrån().getTimme()* 100) + a.getFrån().getMinut();
		int aSlut = (a.getTill().getTimme()* 100) + a.getTill().getMinut();

		int bStart = (b.getFrån().getTimme()* 100) + b.getFrån().getMinut();
		int bSlut = (b.getTill().getTimme()* 100) + b.getTill().getMinut();
		
//		System.out.println("astart = " +aStart + "\naSlut = " + aSlut + "\nbStart = " + bStart + "\nbslut = " + bSlut);
		
//		a.start < b.end && b.start < a.end
		return (aStart < bSlut && bStart < aSlut);
	}
	
	private TidPunkt markeradTidStart = null;
	private ArrayList<Text> markerade = new ArrayList<>();
	private boolean harLämnat = false;
	private Text addMinutTextEgenskaper(Text t, int minut, int timme) {
		
		t.setId(fixaTillMinuter(timme) + ":" + fixaTillMinuter(minut));
		allaMinuter.add(t);
		
//		 System.out.println(t.getId() );//"getid = " +  getId() //+ "\nt.idProperty = " + t.idProperty()
		t.setOnMouseClicked(new EventHandler<MouseEvent>() {
		public void handle(MouseEvent event) {
			markerade.forEach(sak -> sak.setFill(Color.BLACK));
			markerade.clear();
			markerade.add(t);
			
			t.setFill(Color.GREEN);
			event.consume();
		}
	});
//		
//		gridPane.setOnMouseClicked(new EventHandler<MouseEvent>() {
//			public void handle(MouseEvent event) {
//				
//				markeradTid = null;
//				t.setFill(Color.BLACK);
//				event.consume();
//			}
//		});
		
		///-----------------------------............--------------
		t.setOnDragDetected(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
//				if(harLämnat == true){
					harLämnat = false;
					markerade.clear();
//				}
				markerade.add(t);
				
//				Platform.runLater(() -> {
//					if(markerade.size() !=0){
//						markerade.forEach((sak)-> sak.setFill(Color.BLACK));
//						markerade.clear();
//					}
//				});
				int minutTid = gridPane.getRowIndex(t);
				
				int timme = minutTid/60;
				int minut = minutTid - (60*timme);
				markeradTidStart = new TidPunkt(timme, minut);
//				markerade.add(t);
				t.setFill(Color.ORANGE);

				Dragboard db = t.startDragAndDrop(TransferMode.ANY);
				ClipboardContent content = new ClipboardContent();
				content.putString(t.getText() + "kalas");
				db.setContent(content);
				
				event.consume();
			}
		});


		// saker för det som det ska kopieras til
		t.setOnDragOver(new EventHandler<DragEvent>() {
			public void handle(DragEvent event) {
				t.setFill(Color.ORANGE);
//				/* data is dragged over the target */
//				/*
//				 * accept it only if it is not dragged from the same node and if
//				 * it has a string data
//				 */
//				if (event.getGestureSource() != t && event.getDragboard().hasString()) {
//					/*
//					 * allow for both copying and moving, whatever user chooses
//					 */
//					event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
//				}
				event.acceptTransferModes(TransferMode.ANY);

				event.consume();
			}
		});

		t.setOnDragEntered(new EventHandler<DragEvent>() {
			public void handle(DragEvent event) {
				t.setFill(Color.ORANGE);
				markerade.add(t);
				harLämnat = false;
				markerade.forEach(sak -> sak.setFill(Color.ORANGE));
//				/* the drag-and-drop gesture entered the target */
//				/* show to the user that it is an actual gesture target */
//				if (event.getGestureSource() != t && event.getDragboard().hasString()) {
//					t.setFill(Color.GREEN);
//					 Text txt = ((Text) event.getGestureTarget());
//					 System.out.println("hej" + txt.getText());
//					 txt.setFill(Color.GREEN);
//				}

				event.consume();
			}
		});

		
		
		
		t.setOnDragDropped(new EventHandler<DragEvent>() {
			public void handle(DragEvent event) {
				t.setFill(Color.ORANGE);
				markerade.clear();
				
				int minutTid = gridPane.getRowIndex(t);
				
				int timme = minutTid/60;
				int minut = minutTid - (60*timme);
				TidPunkt markeradTidSlut = new TidPunkt(timme, minut);
				
				//ändrar plattsen på dem så de i "programet" "ser ut att vara markerade i rätt årnind" då detta ine påvekar funktionalitet men under lättar följande kod
				if(markeradTidSlut.getTimme()*100 + markeradTidSlut.getMinut() < markeradTidStart.getTimme()*100 + markeradTidStart.getMinut()){
					TidPunkt temp = markeradTidSlut;
					markeradTidSlut = markeradTidStart;
					markeradTidStart = temp;
				}
					
				int startBådaTillsamans = markeradTidStart.getTimme()*100 + markeradTidStart.getMinut();
				int slutBådaTillsamans = markeradTidSlut.getTimme()*100+markeradTidSlut.getMinut();
				
				
				Platform.runLater(() -> {
					markerade.forEach((sak)-> sak.setFill(Color.BLACK));
					markerade.clear();
					
//					System.out.println(markerade.size());
					
					/*ArrayList<Text >utsorterade =*/  allaMinuter.forEach(/* .stream()*//* parallelStream()*//*.filter(*/(Text pp) -> {if(pp instanceof Text){//getChildrenUnmodifiable()
						int timmeFörDel = Integer.parseInt(((Text)pp).getId().split(":")[0]);
						int minutFörDel = Integer.parseInt(((Text)pp).getId().split(":")[1]);
						int bådaTillsamans = timmeFörDel*100 + minutFörDel;
						
//							System.out.println("\ntid: " +((Text)pp).getId()+ "\nbådaTillsamans " +bådaTillsamans + " startBådaTillsamans" + startBådaTillsamans +"\nbådaTillsamans " + bådaTillsamans + " slutBådaTillsamans " + slutBådaTillsamans);
						if(bådaTillsamans >= startBådaTillsamans && bådaTillsamans <= slutBådaTillsamans ){
							((Text) pp).setFill(Color.GREEN);
							System.out.println("kalas");
							markerade.add(((Text) pp));
//							return true;
						}
						
					}
//					return false;
				/*}).forEach((sak) -> {
					markerade.add(sak);
					System.out.println(sak.getId() + " har trixats med och hittats");
				*/});
				});
				
				Dragboard db = event.getDragboard();
				event.setDropCompleted(true);
//				/* data dropped */
//				/* if there is a string data on dragboard, read it and use it */
//				Dragboard db = event.getDragboard();
//				boolean success = false;
//				if (db.hasString()) {
//					t.setText(db.getString());
//					success = true;
//				}
//				/*
//				 * let the source know whether the string was successfully
//				 * transferred and used
//				 */
//				event.setDropCompleted(success);

				event.consume();
			}
		});
		
		
		
		t.setOnDragExited(new EventHandler<DragEvent>() {
			public void handle(DragEvent event) {
				/* mouse moved away, remove the graphical cues */
//				t.setFill(Color.BLACK);
				allaMinuter.forEach(sak -> sak.setFill(Color.BLACK));
//				markerade.forEach(sak -> sak.setFill(Color.BLACK));
				harLämnat = true;
				
				event.consume();
			}
		});

		return t;
	}
	
	


	public ArrayList<CalenderEvent> getAllEvent() {
		ArrayList<CalenderEvent> ret = new ArrayList<>();
		calenderHändelser.forEach(x -> {
			x.forEach(y -> {
				ret.add(y);
			});
		});
		return ret;
	}

	boolean  svar = false;
	public boolean removeEvent(CalenderEvent calenderEvent) {

		svar = false;
		
		long time = System.currentTimeMillis();
		
		ArrayList<CalenderEvent> allEvents = getAllEvent();
		Predicate<CalenderEvent> calenderEventPredict = p-> p.getId() == calenderEvent.getId();
		ArrayList<String> markeradeSparade =  new ArrayList<>();
		markerade.forEach(sak -> markeradeSparade.add(sak.getId()));
		
		if(allEvents.removeIf(calenderEventPredict))
			svar = true;
		
		System.out.println("tid för sparning = " + (System.currentTimeMillis() - time));
		
		time = System.currentTimeMillis();
		clearAll();
		System.out.println("tid för clear = " + (System.currentTimeMillis() - time));
		time = System.currentTimeMillis();
		repaintAll();
		System.out.println("tid för repaint = " + (System.currentTimeMillis() - time));
		time = System.currentTimeMillis();
		for(CalenderEvent ev : allEvents)
			addEvent(ev);

		
		markeradeSparade.forEach(mp -> {
			allaMinuter.stream()
			.filter(am -> am.getId().equalsIgnoreCase(mp))
			.forEach(sak -> {
					markerade.add(sak);
				});
			});
		
		
		markerade.forEach(sak -> sak.setFill(Color.GREEN));
		System.out.println("tid för lägga tillbkaka gamla event = " + (System.currentTimeMillis() - time));
		gridPane.requestLayout();
		System.out.println("Klar");
		return svar;
		
	}


	private void repaintAll() {
		ritaGrundKalender();
		calenderHändelser.add(new ArrayList<CalenderEvent>());

		for (int i = 0; i < 1440; i++) {
	         RowConstraints row = new RowConstraints(minutHöjd);
	         gridPane.getRowConstraints().add(row);
	     }
		
	}


	private void clearAll() {
		calenderHändelser.clear();
		gridPane.getChildren().clear();
		allaMinuter.clear();
		markerade.clear();
	}


	public ArrayList<Text> getGridpane() {//TODO ta bort denna metod bör inte finnas!!!
		return allaMinuter;
	}


	public void getMarkeradeMinuter() {
//		markerade.forEach(x -> System.out.println(x.getId()));
		for(Text t: markerade)
			System.out.println(t.getId());
		System.out.println("-------" + markerade.size());
//		return new CalenderEvent(, till, rubrik, boddy)
	}
}

//System.out.println("till.getTimme = " + till.getTimme() + "\ntill.getMinut = " + till.getMinut());
//System.out.println("från: " + från +"\nTill: " + till);
//System.out.println("slutruta = "  + slutRuta + " startuta = " + startRuta);
