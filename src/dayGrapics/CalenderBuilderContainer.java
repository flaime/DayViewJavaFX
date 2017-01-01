package dayGrapics;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Predicate;

import org.omg.CORBA.Environment;

import com.sun.javafx.geom.Shape;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.CacheHint;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.Separator;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Path;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sun.net.www.content.text.PlainTextInputStream;

public class CalenderBuilderContainer{

	@FXML
	private GridPane gridPane;

	public enum Vy {
		timmVy, minutVy, dagVy
	}
	
	
/**
 * �r som standard minut vy men �ndras h�r till antingen minut, timme eller dags vy	
 * @param vy
 */
	private void setVy(Vy vy){
		vyn = vy;
	}
	private ArrayList<ArrayList<CalenderEvent>> calenderH�ndelser = new ArrayList<>();
	
//	private Vy vyn = Vy.minutVy;
//	Vy vyn = Vy.timmVy;
	private Vy vyn = Vy.dagVy;
	
	private double minutH�jd = -1;
	
	private ArrayList<Node> allaMarkeringar = new ArrayList<>();//allaMarkeringar
	
	public CalenderBuilderContainer(GridPane grid, Vy vyn) {
		this.vyn = vyn;
		gridPane = grid;
		
		gridPane.getChildren().clear();//ska f�rhopnningsvis t�mma gripanen helt s� man kan b�rja med en "ren"
		
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

		ritaGrundKalender();
		calenderH�ndelser.add(new ArrayList<CalenderEvent>());

		for (int i = 0; i < 1440; i++) {
	         RowConstraints row = new RowConstraints(minutH�jd);
	         gridPane.getRowConstraints().add(row);
	     }
	}

	private void ritaGrundKalender() {
		/**
		 * H�r st�ller man in hur stora texter och hur stora som olika minuterna ska vara i de tre olika typerna av vyer
		 * 
		 * det att t�nka p� att tim och dagsvyn minutstorlekln kan �ndras direkt men att minutvyn m�ste. Ska man �ndra storleken p� texten f�r att �ndra hur stora minuterna d�r ska vara.
		 */
		double timVyMinutHeight = 3;
		Font minutVyFont = Font.font("Verdana", 7);
		//bortse fr�n det kommande 4 raderna nedan bara f�r att r�kna ut hur h�ga var minut kommer bli
		Text tillH�jdBer�kning = new Text();
		tillH�jdBer�kning.setText("12");
		tillH�jdBer�kning.setFont(minutVyFont);
		tillH�jdBer�kning.applyCss(); 
		double minutVyMinutHeight = tillH�jdBer�kning.getLayoutBounds().getHeight();
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
				
			    Pane minutPane = new Pane();
			    minutPane.setId(fixaTillMinuter(timme-1) + ":" + fixaTillMinuter(minut-1));
			    	minutPane = (Pane) addMinutTextEgenskaper(minutPane, minut,timme-1);
			    gridPane.add(minutPane,3,i);
			    
				if (vyn == Vy.minutVy) {
					Text t = new Text();
					t.setText(fixaTillMinuter(minut++));
					t.setFont(minutVyFont);
					t.applyCss(); 
				    minutH�jd = Math.ceil(t.getLayoutBounds().getHeight());
				    
				    t = (Text) addMinutTextEgenskaper(t, minut -1,timme-1);

				    
					gridPane.add(t, 2, i);
					
				}else if(vyn == Vy.timmVy){
					Text t = new Text();
					t.setText(fixaTillMinuter(minut++));
					t.setFont(Font.font("Verdana", 12));
					t.applyCss(); 
					t = (Text) addMinutTextEgenskaper(t, minut -1,timme-1);
					
				    
					if(t.getText().matches("\\d0")){
						int heightInMinutes =  (int) Math.ceil(t.getLayoutBounds().getHeight()/timVyMinutHeight);
						gridPane.add(t, 2, i, 1, heightInMinutes);
					}else{
						Pane p = new Pane();
						p.setPrefHeight(timVyMinutHeight);
						gridPane.add(p, 2, i);
					}
					minutH�jd = Math.ceil(timVyMinutHeight);
				}else if(vyn == Vy.dagVy){
					Text t = new Text();
					t.setText(fixaTillMinuter(minut++));
					t.setFont(Font.font("Verdana", 12));
					t.applyCss();
					t = (Text) addMinutTextEgenskaper(t, minut -1,timme-1);
					
				    minutH�jd = Math.ceil(dagVyMinutHeight);// t.getLayoutBounds().getHeight();
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
	
	private StringBuilder sb = new StringBuilder(5);
	private String fixaTillMinuter(int minut){
		sb.setLength(0);
		if((minut+"").length()==1)
			return sb.append("0").append(minut).toString();//"0"+minut;
		else
			return sb.append(minut).toString();//minut+"";
	}
	
	
	public boolean addEvent(TidPunkt fr�n, TidPunkt till, String rubrik, String boddy, boolean scrollbar){
		CalenderEvent event = new CalenderEvent(fr�n, till, rubrik, boddy, scrollbar);
		return addEvent(event);
	}
	public boolean addEvent(TidPunkt fr�n, TidPunkt till, String rubrik, String boddy) {
		CalenderEvent event = new CalenderEvent(fr�n, till, rubrik, boddy);
		return addEvent(event);
	}
	
	public boolean addEvent(CalenderEvent event){	
		//lite fakta om var den ska sitta n�gonstans
		event.addRefrense(this);
		int startRuta = getStartrutaF�rEvent(event);
		int slutRuta = getSlutrutaF�rEvent(event);
		int antalRuterTillSlut = slutRuta - startRuta;
		double maxTill�tnaHeight = maxTill�tanaH�jdP�Event(event);
		
		int niv� = vilkenNiv�SkaEvnetL�ggasP�(event);
		tittaOmNiv�FinnsOmInteL�ggTill(niv�);
		calenderH�ndelser.get(niv�).add(event);//f�r  att kunna tillta var de finns till senare och ta ut h�mta ut h�ndelser
		Node ret = createCalenderEvent(event, maxTill�tnaHeight);
		gridPane.add(ret, 3+niv�, startRuta, 1, antalRuterTillSlut);
		
		

		fixaTillbkakaIckaKrokande();
		fixaAllaIckeKrokande();
		return true;
	}
	
	private ArrayList<CalenderEvent> allaFixadeUtdragna = new ArrayList<>();
	private void fixaAllaIckeKrokande(){
		for(CalenderEvent event :calenderH�ndelser.get(0)){
			boolean krokar = false;
			for(int i =1; i < calenderH�ndelser.size();i++){//ArrayList<CalenderEvent> list : calenderH�ndelser){
				for(CalenderEvent g�mf�raMed : calenderH�ndelser.get(i)){
					if(overlapping(event, g�mf�raMed)){
						krokar = true;
					}
				}
			}
			if(krokar == false){
				ritaUtP�Hela(event);
				allaFixadeUtdragna.add(event);
			}
		}
	}
	private void fixaTillbkakaIckaKrokande(){
		
			for(CalenderEvent eventsak: calenderH�ndelser.get(0)){
				ritaUtP�SinPlatts(eventsak);
			}
	}
	
	private void ritaUtP�SinPlatts(CalenderEvent event){
		final String eventId = event.getId()+"";
		Node hittad = null;
		for(Node �rP�: gridPane.getChildren()){
			if(�rP�.getId() != null)
				if(�rP�.getId().equalsIgnoreCase(eventId))
					hittad = �rP�;
		}
		gridPane.add(createCalenderEvent(event, maxTill�tanaH�jdP�Event(event)), 3/*+vilkenNiv�SkaEvnetL�ggasP�(event)*/, getStartrutaF�rEvent(event),1, getSlutrutaF�rEvent(event)-getStartrutaF�rEvent(event));
		gridPane.getChildren().remove(hittad);
	}
	
	private void ritaUtP�Hela(CalenderEvent event){
		final String eventId = event.getId()+"";
//		gridPane.getChildren().stream()
//			.filter(x-> x.getId() != null)
//			.filter(x->x.getId().equalsIgnoreCase(eventId))
//			.forEach(x->{
//				
//			});
		Node hittad = null;
		for(Node �rP�: gridPane.getChildren()){
			if(�rP�.getId() != null)
				if(�rP�.getId().equalsIgnoreCase(eventId))
					hittad = �rP�;
		}
		gridPane.getChildren().remove(hittad);
		gridPane.add(createCalenderEvent(event, maxTill�tanaH�jdP�Event(event)), 3, getStartrutaF�rEvent(event),calenderH�ndelser.size()+3, getSlutrutaF�rEvent(event)-getStartrutaF�rEvent(event));
	}
	
	

	private double maxTill�tanaH�jdP�Event(CalenderEvent event){
		int antalRuterTillSlut = getSlutrutaF�rEvent(event) - getStartrutaF�rEvent(event);
		return minutH�jd*antalRuterTillSlut;
	}

	private int getSlutrutaF�rEvent(CalenderEvent event) {
		int slutRuta = event.getTill().getTimme()*60;
		slutRuta += event.getTill().getMinut();
		return slutRuta;
	}

	private int getStartrutaF�rEvent(CalenderEvent event) {
		int startRuta = event.getFr�n().getTimme()*60;
		startRuta += event.getFr�n().getMinut();
		return startRuta;
	}
	




	private final Background standardBacroundHedderBackround = new Background(new BackgroundFill(Color.BEIGE, CornerRadii.EMPTY, Insets.EMPTY));
	private ScrollPane createCalenderEvent(CalenderEvent event,double height){ //String rubriken, String boddy, double height){
		
		Text tid = new Text();
		tid.textProperty().bind(event.getFr�nTillObservably());//Bindings.createStringBinding(() -> event.getFr�nTillObservably()+ " "+event.getRubrik()));//Bindings.concat(event.getFr�nTillObservably(), " ",event.getRubrik())); //setText(event.getFr�n() + "-" + event.getTill() + "\t" +event.getRubrik());
		Text rubrik = new Text();
		rubrik.textProperty().bind(event.getRubrikObservably());//Bindings.createStringBinding(() -> event.getFr�nTillObservably()+ " "+event.getRubrik()));//Bindings.concat(event.getFr�nTillObservably(), " ",event.getRubrik())); //setText(event.getFr�n() + "-" + event.getTill() + "\t" +event.getRubrik());
		HBox toppDel = new HBox(tid,new Text(" "),rubrik);
		StackPane rubrikPane = new StackPane(toppDel);//rubrik);
		if(event.getColorHeading() == null)
			rubrikPane.setBackground(standardBacroundHedderBackround);
		else
			rubrikPane.setBackground(new Background(new BackgroundFill(event.getColorHeading(), CornerRadii.EMPTY, Insets.EMPTY)));
		rubrikPane.setId("RubrikPane");
		rubrik.setId("Hedder");
		
		Text bodyn = new Text();
		bodyn.textProperty().bind(event.getBoddyObservably());
		bodyn.setId("Bodyn");
		
		Separator sep = new Separator();
		GridPane kroppen = new GridPane();
		kroppen.setId("kroppen");
		kroppen.setBackground(new Background(new BackgroundFill(event.getColorBody(), CornerRadii.EMPTY, Insets.EMPTY)));
		
		kroppen.add(rubrikPane, 1, 1);
		kroppen.add(bodyn, 1, 3);
		kroppen.add(sep, 1,2);
		ScrollPane ret = new ScrollPane(kroppen);
		ret.setId(event.getId()+"");
		
		ret.setMaxHeight(height);
		ret.setPrefHeight(height);
		ret.setMinHeight(height);
		if(event.isScrollEnabled() == false){
			ret.setHbarPolicy(ScrollBarPolicy.NEVER);
			ret.setVbarPolicy(ScrollBarPolicy.NEVER);
			ret.setFitToWidth(true);
			ret.setFitToHeight(true);
			ret.addEventFilter(ScrollEvent.SCROLL,new EventHandler<ScrollEvent>() {
		        @Override
		        public void handle(ScrollEvent event) {
		            if (event.getDeltaX() != 0) {
		                event.consume();
		            }
		        }
		    });

		}
		
		
		ret.setOnMouseClicked(new EventHandler<MouseEvent>(){
			 
	          @Override
	          public void handle(MouseEvent arg0) {
	             
	        	  Alert alert = new Alert(AlertType.INFORMATION);
	        	  alert.setTitle("Calender h�ndelse");
	        	  alert.setHeaderText(event.getFr�n() + "-" + event.getTill() + " " +event.getRubrik());
	        	  alert.setContentText("bodyn:\n" +event.getBoddy());

	        	  alert.showAndWait();
	          }
	 
	      });
		
		rubrikPane.prefWidthProperty().bind(ret.widthProperty());
		ret.setId(event.getId()+"");
		ret.setCache(true);
		ret.setCacheShape(true);
		ret.setCacheHint(CacheHint.SPEED);
	    return ret;
		
	}
	
	public void uppdateCalenderEventTime(CalenderEvent calenderEvent) {
		if(removeEvent(calenderEvent))
			addEvent(calenderEvent);
	}
	public void uppdateCalenderEventColor(CalenderEvent calenderEvent) {
		gridPane.getChildren().stream()
			.filter(x->x.getId()!=null)
			.filter(x->x.getId().matches(calenderEvent.getId()+""))//"\\d*"))
			.filter(x-> x instanceof ScrollPane)
			.forEach(x->{
				Platform.runLater(() -> {
					System.out.println(x);
					System.out.println(((GridPane)((ScrollPane)x).getContent()).getChildren());
					((GridPane)((ScrollPane)x).getContent()).setBackground(new Background(new BackgroundFill(calenderEvent.getColorBody(), CornerRadii.EMPTY, Insets.EMPTY)));
					((GridPane)((ScrollPane)x).getContent()).getChildren()
						.stream()
						.filter(xy-> xy.getId() != null)
						.forEach(xx->{
							if(xx.getId().equals("Hedder"))
								((StackPane)xx).setBackground(new Background(new BackgroundFill(calenderEvent.getColorHeading(), CornerRadii.EMPTY, Insets.EMPTY)));
							else if(xx.getId().equals("RubrikPane"))
								((StackPane)xx).setBackground(new Background(new BackgroundFill(calenderEvent.getColorHeading(), CornerRadii.EMPTY, Insets.EMPTY)));
//							else if(xx.getId().equals("Bodyn"))
//								((Text)xx).setFill(calenderEvent.getColorBody());
						});
						
				});
			});
//		removeEvent(calenderEvent);
//		addEvent(calenderEvent);
	}

	
	private int vilkenNiv�SkaEvnetL�ggasP�(CalenderEvent event){
		//eventen r�knas 0 som l�ngst till v�nster och sedan h�gre f�r varje kolumen till h�ger
		int niv� =0;
		for(ArrayList<CalenderEvent> array: calenderH�ndelser){
			int niv�innan = niv�;
			for(CalenderEvent testaMot: array){
				if(overlapping(testaMot, event)){
					niv�++;
					break;
				}
				
			}
			if(niv� == niv�innan)
				break;
		}
		return niv�;
	}
	
	private void tittaOmNiv�FinnsOmInteL�ggTill(int niv�){
		if(calenderH�ndelser.size() >= niv�+1){
			
		}else{
			for(int i =0; i < (niv� -calenderH�ndelser.size()+1);i++){
				calenderH�ndelser.add(new ArrayList<CalenderEvent>());
				int timme =0;
				int minut =0;
				for(int y = 0; y < 1440; y++ ){
					Pane minutPane = new Pane();
					minutPane.setCache(true);
					minutPane.setCacheShape(true);
					minutPane.setCacheHint(CacheHint.SPEED);
				    minutPane.setId(fixaTillMinuter(timme) + ":" + fixaTillMinuter(minut));
				    minutPane = (Pane) addMinutTextEgenskaper(minutPane, minut ,timme);
//				    minutPane.setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));
				    gridPane.add(minutPane,calenderH�ndelser.size()-1+3,y);
				    minut++;
				    if(minut == 60){
				    	minut = 0;
				    	timme++;
				    }
				    	
				}
			}
		}
	}
	
	
	public static boolean overlapping(CalenderEvent a, CalenderEvent b){ //�verlapparDessa
		int aStart = (a.getFr�n().getTimme()* 100) + a.getFr�n().getMinut();
		int aSlut = (a.getTill().getTimme()* 100) + a.getTill().getMinut();

		int bStart = (b.getFr�n().getTimme()* 100) + b.getFr�n().getMinut();
		int bSlut = (b.getTill().getTimme()* 100) + b.getTill().getMinut();
		
		return (aStart < bSlut && bStart < aSlut);
	}
	
	private TidPunkt markedTimeStart = null;
	private TidPunkt markedTimeEnd = null;
//	private ArrayList<Text> markerade = new ArrayList<>();
	private boolean harL�mnat = false;
	private boolean dropped = true;
	
	private Background bakrundBlack = new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY));
	private Background bakrundWhite = new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY));
	private Background bakrundGren = new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY));
	private Background bakrundOrange = new Background(new BackgroundFill(Color.ORANGE, CornerRadii.EMPTY, Insets.EMPTY));
	private Node addMinutTextEgenskaper(Node t, int minut, int timme) {

		t.setId(fixaTillMinuter(timme) + ":" + fixaTillMinuter(minut));
//		allaMinuter.add(t);

		t.setOnMouseClicked(event->{
			Color f�rg = Color.BLACK;
//			if(t instanceof Pane){
//				((Pane) t).setBackground(new Background(new BackgroundFill(f�rg, CornerRadii.EMPTY, Insets.EMPTY)));
//			}else{
//				((Text) t).setFill(f�rg);
//			}
				
//			 markerade.forEach(sak -> sak.setFill(Color.BLACK));
			long starttid = System.currentTimeMillis();
			System.out.println("start");
			if (markedTimeEnd != null && markedTimeStart != null) {
				colorMinutes(markedTimeStart, markedTimeEnd, Color.BLACK, bakrundWhite);
			} else if (markedTimeStart != null) {
				colorMinutes(markedTimeStart, markedTimeStart, Color.BLACK, bakrundWhite);
			}
			
			long tidNu = System.currentTimeMillis();
			long tid = tidNu-starttid;
			System.out.println("ritat ut f�rsta g�ngen tog:"+tid);

			int minutTid = gridPane.getRowIndex(t);
			int timmeTimme = minutTid / 60;
			int minutMinut = minutTid - (60 * timmeTimme);
			markedTimeStart = new TidPunkt(timmeTimme, minutMinut);
			markedTimeEnd = null;
//			markerade.clear();
//			markerade.add(t);
			
			tid = System.currentTimeMillis() -tidNu;
			tidNu = System.currentTimeMillis();
			System.out.println("tid efter mittenber�knigar:\n"+tid);
			if (markedTimeEnd != null && markedTimeStart != null) {
				colorMinutes(markedTimeStart, markedTimeEnd, Color.GREEN,bakrundGren);
			} else if (markedTimeStart != null) {
				colorMinutes(markedTimeStart, markedTimeStart, Color.GREEN,bakrundGren);
			}
			event.consume();
			repaintAll();
			System.out.println("tid till slute:\n"+(System.currentTimeMillis()-tidNu));
		});

		
		///-----------------------------............--------------
		t.setOnDragDetected(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				if(markedTimeEnd != null && markedTimeStart !=null)
					colorMinutes(markedTimeStart, markedTimeEnd, Color.BLACK, bakrundWhite);
				else if(markedTimeStart != null){
					colorMinutes(markedTimeStart, markedTimeStart, Color.BLACK,bakrundWhite);
				}
				
//				Platform.runLater(() -> {
//					if(markerade.size() !=0){
//						markerade.forEach((sak)-> sak.setFill(Color.BLACK));
//						markerade.clear();
//					}
//				});
					
				markedTimeEnd = null;
				dropped = false;
//				if(harL�mnat == true){
					harL�mnat = false;
//					markerade.clear();
//				}
//				markerade.add((Text) t);
				
//				Platform.runLater(() -> {
//					if(markerade.size() !=0){
//						markerade.forEach((sak)-> sak.setFill(Color.BLACK));
//						markerade.clear();
//					}
//				});
				int minutTid = gridPane.getRowIndex(t);
				
				int timme = minutTid/60;
				int minut = minutTid - (60*timme);
				markedTimeStart = new TidPunkt(timme, minut);
//				markerade.add(t);
//				t.setFill(Color.ORANGE);
				if(markedTimeEnd != null && markedTimeStart !=null)
					colorMinutes(markedTimeStart, markedTimeEnd, Color.ORANGE, bakrundOrange );
				else if(markedTimeStart != null){
					colorMinutes(markedTimeStart, markedTimeStart, Color.ORANGE,bakrundOrange );
				}

				Dragboard db = t.startDragAndDrop(TransferMode.ANY);
				ClipboardContent content = new ClipboardContent();
				content.putString("hej");//t.getText() + "kalas");
				db.setContent(content);
				
				event.consume();
			}
		});


		// saker f�r det som det ska kopieras til
		t.setOnDragOver(new EventHandler<DragEvent>() {
			public void handle(DragEvent event) {
//				int minutTid = gridPane.getRowIndex(t);
//				
//				int timme = minutTid/60;
//				int minut = minutTid - (60*timme);
//				TidPunkt markeradTidSlutsak = new TidPunkt(timme, minut);
//				markeradTidSlut = markeradTidSlutsak;
				
				setMarkeradSlutTid(t);
				if(markedTimeEnd != null && markedTimeStart !=null)
					colorMinutes(markedTimeStart, markedTimeEnd, Color.ORANGE, bakrundOrange );
				else if(markedTimeStart != null){
					colorMinutes(markedTimeStart, markedTimeStart, Color.ORANGE,bakrundOrange );
				}
				
//				((Text)t).setFill(Color.ORANGE);
				
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
//				setMarkeradSlutTid(t);
//				if(markeradTidSlut != null && markeradTidStart !=null)
//					f�rgaMinuter(markeradTidStart, markeradTidSlut, Color.BLACK, Color.WHITE);
//				else if(markeradTidStart != null){
//					f�rgaMinuter(markeradTidStart, markeradTidStart, Color.BLACK,Color.WHITE);
//				}
//				t.setFill(Color.ORANGE);
//				markerade.add(t);
				harL�mnat = false;
//				markerade.forEach(sak -> sak.setFill(Color.ORANGE));
				int minutTid = gridPane.getRowIndex(t);
				
				int timme = minutTid/60;
				int minut = minutTid - (60*timme);
				TidPunkt markeradTidSlut = new TidPunkt(timme, minut);
//				f�rgaMinuter(markeradTidStart,markeradTidSlut,Color.ORANGE,Color.ORANGE);
				setMarkeradSlutTid(t);
				if(markeradTidSlut != null && markedTimeStart !=null)
					colorMinutes(markedTimeStart, markeradTidSlut, Color.ORANGE, bakrundOrange );
				else if(markedTimeStart != null){
					colorMinutes(markedTimeStart, markedTimeStart, Color.ORANGE,bakrundOrange );
				}
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
				dropped =true;
//				t.setFill(Color.ORANGE);
//				markerade.clear();
				
				int minutTid = gridPane.getRowIndex(t);
				
				int timme = minutTid/60;
				int minut = minutTid - (60*timme);
				TidPunkt markeradTidSlutsak = new TidPunkt(timme, minut);
				markedTimeEnd = markeradTidSlutsak;
				colorMinutes(markedTimeStart,markeradTidSlutsak,Color.GREEN, bakrundGren);
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
//				allaMinuter.forEach(sak -> sak.setFill(Color.BLACK));
				if(dropped == false){
					setMarkeradSlutTid(t);
					if(markedTimeEnd != null && markedTimeStart !=null)
						colorMinutes(markedTimeStart, markedTimeEnd, Color.BLACK, bakrundWhite);
					else if(markedTimeStart != null){
						colorMinutes(markedTimeStart, markedTimeStart, Color.BLACK,bakrundWhite);
					}
					harL�mnat = true;
					
					if(dropped == false){
						int minutTid = gridPane.getRowIndex(t);
						
						int timme = minutTid/60;
						int minut = minutTid - (60*timme);
						TidPunkt markeradTidSlut = new TidPunkt(timme, minut);
						colorMinutes(markedTimeStart,markeradTidSlut,Color.BLACK, bakrundWhite);
					}	
					event.consume();
				}
			}
		});

		return t;
	}
	
	private void setMarkeradSlutTid(Node t){
		int minutTid = gridPane.getRowIndex(t);
		
		int timme = minutTid/60;
		int minut = minutTid - (60*timme);
		TidPunkt markeradTidSlutsak = new TidPunkt(timme, minut);
		markedTimeEnd = markeradTidSlutsak;
	}
	private void f�rgaMinuter(TidPunkt markeradTidStart, TidPunkt markeradTidSlut, Color f�rgText, Color f�rgAnnat) {
		colorMinutes(markeradTidStart, markeradTidSlut, f�rgText, new Background(new BackgroundFill(f�rgAnnat, CornerRadii.EMPTY, Insets.EMPTY)));
	}
	private void colorMinutes(TidPunkt markeradTidStart, TidPunkt markeradTidSlut, Color f�rgText, Background barkundsf�rg) {
		
		System.out.println("anropet kommer");
		// �ndrar plattsen p� dem s� de i "programet" "ser ut att vara markerade
		// i r�tt �rnind" d� detta ine p�vekar funktionalitet men under l�ttar
		// f�ljande kod
		if (markeradTidSlut.getTimme() * 100 + markeradTidSlut.getMinut() < markeradTidStart.getTimme() * 100
				+ markeradTidStart.getMinut()) {
			TidPunkt temp = markeradTidSlut;
			markeradTidSlut = markeradTidStart;
			markeradTidStart = temp;
		}
		int startB�daTillsamans = markeradTidStart.getTimme() * 100 + markeradTidStart.getMinut();
		int slutB�daTillsamans = markeradTidSlut.getTimme() * 100 + markeradTidSlut.getMinut();
		Platform.runLater(() -> {
//		Platform.runLater(() -> {
//			markerade.forEach((sak) -> sak.setFill(Color.BLACK));
//			markerade.clear();
//		});
		System.out.println("innan filter b�rjar");
		gridPane.getChildren().stream()//parallelStream()
			.filter(x-> x.getId()!=null)
			.filter(y-> y.getId().matches("\\d\\d:\\d\\d"))
			.filter(pp->{
				int timmeF�rDel = Integer.parseInt(((Node) pp).getId().split(":")[0]);
				int minutF�rDel = Integer.parseInt(((Node) pp).getId().split(":")[1]);
				int b�daTillsamans = timmeF�rDel * 100 + minutF�rDel;
//				System.out.println("pp="+pp);
				if (b�daTillsamans >= startB�daTillsamans && b�daTillsamans <= slutB�daTillsamans)
					return true;
				else 
					return false;
			})
			.forEach(pp->{
//				Platform.runLater(() -> {
					System.out.println("�ndar f�rgen nu----");
					if(pp instanceof Pane){
//						((Pane) pp).setBackground(barkundsf�rg);
//						markerade.add(((Pane) pp));
					}else{
	//					final Text text = ((Text) pp);
						((Text) pp).setFill(f�rgText);
//						markerade.add(((Text) pp));
					}
//				});
			});
		
		});
		
	}

	public ArrayList<CalenderEvent> getAllEvent() {
		ArrayList<CalenderEvent> ret = new ArrayList<>();
		calenderH�ndelser.forEach(x -> {
			x.forEach(y -> {
				ret.add(y);
			});
		});
		return ret;
	}

	private boolean  svar = false;
	public boolean removeEvent(CalenderEvent calenderEvent) {
		
		svar = false;
		
		long time = System.currentTimeMillis();
		
		ArrayList<CalenderEvent> allEvents = getAllEvent();
		Predicate<CalenderEvent> calenderEventPredict = p-> p.getId() == calenderEvent.getId();
		TidPunkt sparadStartMarkerad = null;
		TidPunkt sparadSlutMarkerad = null;
		sparadSlutMarkerad = markedTimeEnd;
		sparadStartMarkerad = markedTimeStart;
		
		if(allEvents.removeIf(calenderEventPredict)){
			svar = true;
			calenderEvent.removeRefrence(this);
		}
		
		System.out.println("tid f�r sparning = " + (System.currentTimeMillis() - time));
		
		time = System.currentTimeMillis();
		clearAll();
		System.out.println("tid f�r clear = " + (System.currentTimeMillis() - time));
		time = System.currentTimeMillis();
		repaintAll();
		System.out.println("tid f�r repaint = " + (System.currentTimeMillis() - time));
		time = System.currentTimeMillis();
		for(CalenderEvent ev : allEvents)
			addEvent(ev);

		markedTimeStart = sparadStartMarkerad;
		markedTimeEnd = sparadSlutMarkerad;
		if(markedTimeEnd != null && markedTimeStart !=null)
			colorMinutes(markedTimeStart,markedTimeEnd,Color.GREEN, bakrundGren);
		else if(markedTimeStart !=null)
			colorMinutes(markedTimeStart,markedTimeStart,Color.GREEN, bakrundGren);
		//		markeradeSparade.forEach(mp -> {
//			allaMinuter.stream()
//			.filter(am -> am.getId().equalsIgnoreCase(mp))
//			.forEach(sak -> {
//					markerade.add(sak);
//				});
//			});
		
//		markerade.forEach(sak -> sak.setFill(Color.GREEN));
		System.out.println("tid f�r l�gga tillbkaka gamla event = " + (System.currentTimeMillis() - time));
		gridPane.requestLayout();
		System.out.println("Klar");
		return svar;
		
	}

	private void repaintAll() {
		ritaGrundKalender();
		calenderH�ndelser.add(new ArrayList<CalenderEvent>());

		for (int i = 0; i < 1440; i++) {
	         RowConstraints row = new RowConstraints(minutH�jd);
	         gridPane.getRowConstraints().add(row);
	     }
		
	}

	private void clearAll() {
		calenderH�ndelser.clear();
		gridPane.getChildren().clear();
//		allaMinuter.clear();
//		markerade.clear();
	}

	public TidPunkt[] getMarkedMinutes() {
		TidPunkt[] ret = new TidPunkt[2];
		if(markedTimeEnd != null && markedTimeStart !=null){
			ret[0] = markedTimeStart;
			ret[1] = markedTimeEnd;
		}else if(markedTimeStart !=null){
			ret[0] = markedTimeStart;
			ret[1] = markedTimeStart;
		}
		
		return ret;
	}

	private TidPunkt getTidpunkFromText(String text) {
		String[] tal = text.split(":");
		if(tal.length !=2)
			return null;
		try {
			return new TidPunkt(Integer.parseInt(tal[0]), Integer.parseInt(tal[1]));
		} catch (NumberFormatException e) {
			return null;
		}
		
	}

	public void setMarkedTime(TidPunkt startTime, TidPunkt endTime) {
		if(markedTimeEnd != null && markedTimeStart !=null)
			colorMinutes(markedTimeStart, markedTimeEnd, Color.BLACK, bakrundWhite);
		else if(markedTimeStart != null){
			colorMinutes(markedTimeStart, markedTimeStart, Color.BLACK,bakrundWhite);
		}
		markedTimeEnd = endTime;
		markedTimeStart = startTime;
		
		if (markedTimeEnd != null && markedTimeStart != null) {
			colorMinutes(markedTimeStart, markedTimeEnd, Color.GREEN,bakrundGren);
		} else if (markedTimeStart != null) {
			colorMinutes(markedTimeStart, markedTimeStart, Color.GREEN,bakrundGren);
		}
		
	}

	public void clearSelectedTime() {
		if(markedTimeEnd != null && markedTimeStart !=null)
			colorMinutes(markedTimeStart, markedTimeEnd, Color.BLACK, bakrundWhite);
		else if(markedTimeStart != null){
			colorMinutes(markedTimeStart, markedTimeStart, Color.BLACK,bakrundWhite);
		}
		markedTimeEnd = null;
		markedTimeStart = null;
		
	}



}
