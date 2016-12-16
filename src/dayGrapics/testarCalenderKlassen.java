package dayGrapics;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.event.ActionEvent;


public class testarCalenderKlassen extends Application {
	
	
	
//	public void initialize() {
//		Button btn = new Button();
//        btn.setText("Say 'Hello World'");
//        btn.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                System.out.println("Hello World!");
//            }
//        });
//	}
	CalenderBuilderContainer cbc;
    public void start(Stage stage) {
//        Circle circ = new Circle(40, 40, 30);
//        Group root = new Group(circ);
//    	GridPane  gridpane = new GridPane();
//    	ScrollPane sp = new ScrollPane(gridpane);
//    	
//    	AnchorPane root = new AnchorPane();
//    	AnchorPane.setTopAnchor(sp, 0.0);
//    	AnchorPane.setLeftAnchor(sp, 0.0);
//    	AnchorPane.setRightAnchor(sp, 0.0);
//    	AnchorPane.setBottomAnchor(sp, -20.0);
//    	
//    	Button knapp = new Button("längst ner knappt");
//    	AnchorPane.setTopAnchor(knapp, 30.0);
//    	AnchorPane.setLeftAnchor(knapp, 0.0);
//    	AnchorPane.setRightAnchor(knapp, 0.0);
//    	AnchorPane.setBottomAnchor(knapp, 0.0);
//    	
//    	
    	
    	
    	
    	
    	
    	

    	GridPane  r1 = new GridPane();
//    	r1.setFill(Color.LIGHTGRAY);
    	Button r2 = new Button("ta bort");
    	r2.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
            	System.out.println("Button clicked");
      		  cbc.removeEvent(cbc.getAllEvent().get(0));
      		  cbc.removeEvent(cbc.getAllEvent().get(0));
      		  System.out.println("mer klar");
      		  
      		  cbc.getMarkeradeMinuter();
            }
        });
//    	r2.setOnAction((event) -> {
//    		  System.out.println("Button clicked");
//    		  cbc.removeEvent(cbc.getAllEvent().get(0));
//    		  cbc.removeEvent(cbc.getAllEvent().get(0));
//    		  System.out.println("mer klar");
//    	});
    	Button r3 = new Button("lägg till");
    	r3.setOnAction((event) -> {
    		  System.out.println("Button clicked");
    		  cbc.addEvent(new TidPunkt(0, 10), new TidPunkt(00, 20), "ja från annat ställe", "lite längre text här\nNyrad\nhej\nkalas\nhkalas");
    		  cbc.addEvent(new TidPunkt(0, 20), new TidPunkt(00, 30), "ja från annat ställe", "lite längre text här\nNyrad\nhej\nkalas\nhkalas");
    		  System.out.println("mer klar");
    		  
    		   cbc.getGridpane().forEach(sak -> System.out.println("-"+sak.getId()));
    		   System.out.println(cbc.getGridpane().size() + "storlek");
    	});
    	
    	
    	
//    	Rectangle r2 = new Rectangle(200,400);
//    	r2.setFill(Color.DARKGRAY);
    	AnchorPane anchor = new AnchorPane(r1, r2, r3);
    	
    	AnchorPane.setTopAnchor(r1, 0.0);
    	AnchorPane.setLeftAnchor(r1, 0.0);
    	
    	AnchorPane.setTopAnchor(r2, 00.0);
//    	AnchorPane.setRightAnchor(r2, 00.0);
    	AnchorPane.setLeftAnchor(r2, 20.0);
    	
    	AnchorPane.setTopAnchor(r3, 00.0);
    	AnchorPane.setLeftAnchor(r3, 100.0);
//    	AnchorPane.setRightAnchor(r3, 500.0);
//    	AnchorPane.setBottomAnchor(r3, 0.0);
    	
    	
    	Group roott = new Group(anchor);
        Scene scene = new Scene(roott, 400, 500);
//        scene.addEventFilter(MouseEvent.ANY, e -> System.out.println( e));
        
        cbc = new CalenderBuilderContainer(r1);
        
        stage.setTitle("My JavaFX Application");
        stage.setScene(scene);
        stage.show();
        cbc.addEvent(new TidPunkt(0, 10), new TidPunkt(00, 20), "ja från annat ställe", "lite längre text här\nNyrad\nhej\nkalas\nhkalas");
//        cbc.removeEvent(cbc.getAllEvent().get(0));
//        cbc.removeEvent(cbc.getAllEvent().get(2));
//        cbc.removeEvent(cbc.getAllEvent().get(3));
//        cbc.removeEvent(cbc.getAllEvent().get(4));
    }
    
    public static void main(String[] args) {
    	
//		testarCalenderKlassen tk = new testarCalenderKlassen();
		System.out.println("hej");
		
		Application.launch(args);
	}
}


/*
root.setTopAnchor(sp, 0.0);
root.setLeftAnchor(sp, 0.0);
root.setRightAnchor(sp, 0.0);
root.setBottomAnchor(sp, -20.0);

Button knapp = new Button("längst ner knappt");
AnchorPane.setTopAnchor(knapp, 30.0);
root.setLeftAnchor(knapp, 0.0);
root.setRightAnchor(knapp, 0.0);
root.setBottomAnchor(knapp, 0.0);
*/