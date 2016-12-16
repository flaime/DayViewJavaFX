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
    	Button r3 = new Button("lägg till");
    	r3.setOnAction((event) -> {
    		  System.out.println("Button clicked");
    		  cbc.addEvent(new TidPunkt(0, 10), new TidPunkt(00, 20), "ja från annat ställe", "lite längre text här\nNyrad\nhej\nkalas\nhkalas", false);
    		  cbc.addEvent(new TidPunkt(0, 20), new TidPunkt(00, 30), "ja från annat ställe", "lite längre text här\nNyrad\nhej\nkalas\nhkalas", false);
    		  System.out.println("mer klar");
    		  
    		   cbc.getGridpane().forEach(sak -> System.out.println("-"+sak.getId()));
    		   System.out.println(cbc.getGridpane().size() + "storlek");
    	});
    	
    	
    	
    	AnchorPane anchor = new AnchorPane(r1, r2, r3);
    	
    	AnchorPane.setTopAnchor(r1, 0.0);
    	AnchorPane.setLeftAnchor(r1, 0.0);
    	
    	AnchorPane.setTopAnchor(r2, 00.0);
    	AnchorPane.setLeftAnchor(r2, 20.0);
    	
    	AnchorPane.setTopAnchor(r3, 00.0);
    	AnchorPane.setLeftAnchor(r3, 100.0);
    	
    	
    	Group roott = new Group(anchor);
        Scene scene = new Scene(roott, 400, 500);
        
        cbc = new CalenderBuilderContainer(r1);
        
        stage.setTitle("My JavaFX Application");
        stage.setScene(scene);
        stage.show();
        cbc.addEvent(new TidPunkt(0, 10), new TidPunkt(00, 20), "ja från annat ställe", "lite längre text här\nNyrad\nhej\nkalas\nhkalas", false);
//        cbc.removeEvent(cbc.getAllEvent().get(3)); //varör går detta så mycket snabbare TODO?
    }
    
    public static void main(String[] args) {
    	
//		testarCalenderKlassen tk = new testarCalenderKlassen();
		System.out.println("hej");
		
		Application.launch(args);
	}
}