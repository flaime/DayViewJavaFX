package dayGrapics;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
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
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class DagsViewController extends Application {

	@FXML
	private Text source;
	@FXML
	private Text target;
	@FXML
	private GridPane gridPane;

	public enum Vy {
		timmVy, minutVy, dagVy
	}
	
	public void setVy(Vy vy){
		vyn = vy;
	}
	private ArrayList<ArrayList<CalenderEvent>> calenderHändelser = new ArrayList<>();
	
//	private KalenderInehållFast 

	Vy vyn = Vy.minutVy;
//	Vy vyn = Vy.timmVy;
//	Vy vyn = Vy.dagVy;
	
	double minutHöjd = -1;
	
	// final Text source = new Text(50, 100, "DRAG ME");
	// final Text target = new Text(300, 100, "DROP HERE");

	/**
	 * The constructor. The constructor is called before the initialize()
	 * method.
	 */
	public DagsViewController() {

	}

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
		

		// sätter dragblörjan saker
		source.setOnDragDetected(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				/* drag was detected, start a drag-and-drop gesture */
				/* allow any transfer mode */
				Dragboard db = source.startDragAndDrop(TransferMode.ANY);

				/* Put a string on a dragboard */
				ClipboardContent content = new ClipboardContent();
				content.putString(source.getText());
				db.setContent(content);

				event.consume();
			}
		});

		// saker för det som det ska kopieras til
		target.setOnDragOver(new EventHandler<DragEvent>() {
			public void handle(DragEvent event) {
				/* data is dragged over the target */
				/*
				 * accept it only if it is not dragged from the same node and if
				 * it has a string data
				 */
				if (event.getGestureSource() != target && event.getDragboard().hasString()) {
					/*
					 * allow for both copying and moving, whatever user chooses
					 */
					event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
				}

				event.consume();
			}
		});

		target.setOnDragEntered(new EventHandler<DragEvent>() {
			public void handle(DragEvent event) {
				/* the drag-and-drop gesture entered the target */
				/* show to the user that it is an actual gesture target */
				if (event.getGestureSource() != target && event.getDragboard().hasString()) {
					target.setFill(Color.GREEN);
				}

				event.consume();
			}
		});

		target.setOnDragExited(new EventHandler<DragEvent>() {
			public void handle(DragEvent event) {
				/* mouse moved away, remove the graphical cues */
				target.setFill(Color.BLACK);

				event.consume();
			}
		});

		target.setOnDragDropped(new EventHandler<DragEvent>() {
			public void handle(DragEvent event) {
				/* data dropped */
				/* if there is a string data on dragboard, read it and use it */
				Dragboard db = event.getDragboard();
				boolean success = false;
				if (db.hasString()) {
					target.setText(db.getString());
					success = true;
				}
				/*
				 * let the source know whether the string was successfully
				 * transferred and used
				 */
				event.setDropCompleted(success);

				event.consume();
			}
		});

		source.setOnDragDone(new EventHandler<DragEvent>() {
			public void handle(DragEvent event) {
				/* the drag and drop gesture ended */
				/* if the data was successfully moved, clear it */
				if (event.getTransferMode() == TransferMode.MOVE) {
					source.setText("");
				}
				event.consume();
			}
		});

		
		
		
		
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
		System.out.println("andra hejet");
	}

	private BorderPane rootLayout;
	private Stage primaryStage;

	public static void main(String[] args) {
		
//		System.out.println("välkommen Linus");
//		CalenderEvent event1 = new CalenderEvent(new TidPunkt(0, 32), new TidPunkt(0, 37), "Veckans försla lopp", "ja en heäldese men som inte har så mycket texti sig\nadsfa\nasdfaf\nasdfa\nhej\nkalas");
//		CalenderEvent event2 = new CalenderEvent(new TidPunkt(0, 35), new TidPunkt(0, 40), "Veckans försla lopp", "ja en heäldese men som inte har så mycket texti sig\nadsfa\nasdfaf\nasdfa\nhej\nkalas");
//		CalenderEvent event3 = new CalenderEvent(new TidPunkt(0, 37), new TidPunkt(0, 42), "Veckans försla lopp", "ja en heäldese men som inte har så mycket texti sig\nadsfa\nasdfaf\nasdfa\nhej\nkalas");
//		
//		DagsViewController dvc = new DagsViewController();
//		dvc.calenderHändelser.add(new ArrayList<CalenderEvent>());
//		System.out.println("nivån = " + dvc.vilkenNivåSkaEvnetLäggasPå(event1));
//		dvc.calenderHändelser.get(dvc.vilkenNivåSkaEvnetLäggasPå(event1)).add(event1);
//		
//		System.out.println("nivån för nummer 2 = " + dvc.vilkenNivåSkaEvnetLäggasPå(event2));
//		dvc.tittaOmNivåFinnsOmInteLäggTill(dvc.vilkenNivåSkaEvnetLäggasPå(event2));
//		dvc.calenderHändelser.get(dvc.vilkenNivåSkaEvnetLäggasPå(event2)).add(event2);
//		
//		System.out.println("nivån för nummer 3 = " + dvc.vilkenNivåSkaEvnetLäggasPå(event3));
//		dvc.tittaOmNivåFinnsOmInteLäggTill(dvc.vilkenNivåSkaEvnetLäggasPå(event3));
//		dvc.calenderHändelser.get(dvc.vilkenNivåSkaEvnetLäggasPå(event3)).add(event3);
//		
//		
//		System.out.println("Överlappar det = " + överlapparDessa(event2, event1));
//		
//		
//		
//		
		
		
//		int nivå = vilkenNivåSkaEvnetLäggasPå(event);
//		tittaOmNivåFinnsOmInteLäggTill(nivå);
//		calenderHändelser.get(nivå).add(event);
//		
		
		
		
		
		Application.launch(DagsViewController.class, args);
		
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		Parent root = FXMLLoader.load(getClass().getResource("DagsvyView.fxml"));

		primaryStage.setTitle("FXML Welcome");
		primaryStage.setScene(new Scene(root, 1200, 1400));
		primaryStage.show();
		
		retList.forEach(x -> {System.out.println("höjden =" + x.getHeight());});
		System.out.println(retList.size());
		System.out.println("hej");
	}

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
			int timme = 1;
			for (int i = 0; i < 1440; i++) {
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
				    
				    t = addMinutTextEgenskaper(t);
					gridPane.add(t, 2, i);
					
//					gridPane.add(child, 3, i);
				}else if(vyn == Vy.timmVy){
					Text t = new Text();
					t.setText(fixaTillMinuter(minut++));
					t.setFont(Font.font("Verdana", 12));
					t.applyCss(); 
				    
					
				    
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
		//lite fakta om var den ska sitta någonstans
		int startRuta = från.getTimme()*60;
		startRuta += från.getMinut();
		int slutRuta = till.getTimme()*60;
		slutRuta += till.getMinut();
		int antalRuterTillSlut = slutRuta - startRuta;
		double maxTillåtnaHeight = minutHöjd*antalRuterTillSlut;
		
//		if(finnsEventPåTiden(från,till)==false)
		CalenderEvent event = new CalenderEvent(från, till, rubrik, boddy);
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
	
	private Text addMinutTextEgenskaper(Text t) {
		// sätter dragblörjan saker
//				t.setOnDragDetected(new EventHandler<MouseEvent>() {
//					public void handle(MouseEvent event) {
//						/* drag was detected, start a drag-and-drop gesture */
//						/* allow any transfer mode */
//						Dragboard db = source.startDragAndDrop(TransferMode.ANY);
//
//						/* Put a string on a dragboard */
//						ClipboardContent content = new ClipboardContent();
//						content.putString(source.getText());
//						db.setContent(content);
//
//						event.consume();
//					}
//				});

				// saker för det som det ska kopieras til
				t.setOnDragOver(new EventHandler<DragEvent>() {
					public void handle(DragEvent event) {
						/* data is dragged over the target */
						/*
						 * accept it only if it is not dragged from the same node and if
						 * it has a string data
						 */
						if (event.getGestureSource() != target && event.getDragboard().hasString()) {
							/*
							 * allow for both copying and moving, whatever user chooses
							 */
							event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
						}

						event.consume();
					}
				});

				t.setOnDragEntered(new EventHandler<DragEvent>() {
					public void handle(DragEvent event) {
						/* the drag-and-drop gesture entered the target */
						/* show to the user that it is an actual gesture target */
						if (event.getGestureSource() != target && event.getDragboard().hasString()) {
							target.setFill(Color.GREEN);
							 Text txt = ((Text) event.getGestureTarget());
							 System.out.println("hej" + txt.getText());
//							 txt.setFill(Color.GREEN);
						}

						event.consume();
					}
				});

				t.setOnDragExited(new EventHandler<DragEvent>() {
					public void handle(DragEvent event) {
						/* mouse moved away, remove the graphical cues */
						target.setFill(Color.BLACK);

						event.consume();
					}
				});

				t.setOnDragDropped(new EventHandler<DragEvent>() {
					public void handle(DragEvent event) {
						/* data dropped */
						/* if there is a string data on dragboard, read it and use it */
						Dragboard db = event.getDragboard();
						boolean success = false;
						if (db.hasString()) {
							target.setText(db.getString());
							success = true;
						}
						/*
						 * let the source know whether the string was successfully
						 * transferred and used
						 */
						event.setDropCompleted(success);

						event.consume();
					}
				});

				t.setOnDragDone(new EventHandler<DragEvent>() {
					public void handle(DragEvent event) {
						/* the drag and drop gesture ended */
						/* if the data was successfully moved, clear it */
						if (event.getTransferMode() == TransferMode.MOVE) {
							source.setText("");
						}
						event.consume();
					}
				});
		return t;
	}
}

//System.out.println("till.getTimme = " + till.getTimme() + "\ntill.getMinut = " + till.getMinut());
//System.out.println("från: " + från +"\nTill: " + till);
//System.out.println("slutruta = "  + slutRuta + " startuta = " + startRuta);
