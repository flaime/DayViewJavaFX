package dayGrapics;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
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
    		   r1.setMaxWidth(400);
    		   for(Object ob :cbc.getChildren()){
    	        	if(((Node)ob).getId() != null){
    		        	if(((Node)ob).getId().split(":").length == 1 ){
    		        		System.out.println(ob);
    		        	}
    	        	}
    	        }
    	});
    	
    	Pane p = new Pane();
    	p.setId("kalas");
    	System.out.println("id = " + p.getId());
    	GridPane gp = new GridPane();
    	gp.add(p, 1, 1);
    	gp.getChildren().forEach(x -> System.out.println(x.getId()));
    	
    	
    	AnchorPane anchor = new AnchorPane(r1, r2, r3);
    	
    	AnchorPane.setTopAnchor(r1, 0.0);
    	AnchorPane.setLeftAnchor(r1, 0.0);
    	
    	AnchorPane.setTopAnchor(r2, 00.0);
    	AnchorPane.setLeftAnchor(r2, 20.0);
    	
    	AnchorPane.setTopAnchor(r3, 00.0);
    	AnchorPane.setLeftAnchor(r3, 100.0);
    	
    	
    	Group roott = new Group(anchor);
        Scene scene = new Scene(roott, 600, 900);
        
        cbc = new CalenderBuilderContainer(r1);
        r1.setMaxWidth(200);
        
        stage.setTitle("My JavaFX Application");
        stage.setScene(scene);
        stage.show();
        CalenderEvent ce1 =  new CalenderEvent(new TidPunkt(0, 10), new TidPunkt(00, 19), "ett", "lite längre text här\nNyrad\nhej\nkalas\nhkalas", false);
        cbc.addEvent(ce1);
        cbc.addEvent(new TidPunkt(0, 20), new TidPunkt(00, 30), "två", "lite längre text här\nNyrad\nhej\nkalas\nhkalas", false);
        cbc.addEvent(new TidPunkt(0, 30), new TidPunkt(00, 40), "tre", "lite längre text här\nNyrad\nhej\nkalas\nhkalas", false);
        CalenderEvent ce2 = new CalenderEvent(new TidPunkt(0, 40), new TidPunkt(00, 50), "tre", "lite längre text här\nNyrad\nhej\nkalas\nhkalas", false);
        cbc.addEvent(ce2);
        CalenderEvent ce3 = new CalenderEvent(new TidPunkt(0, 45), new TidPunkt(00, 55), "tre", "lite längre text här\nNyrad\nhej\nkalas\nhkalas", false);
        cbc.addEvent(ce3);
        cbc.addEvent(new TidPunkt(0, 50), new TidPunkt(00, 59), "tre", "lite längre text här\nNyrad\nhej\nkalas\nhkalas", false);
        cbc.addEvent(new TidPunkt(0, 50), new TidPunkt(00, 55), "tre", "lite längre text här\nNyrad\nhej\nkalas\nhkalas", false);
        cbc.addEvent(new TidPunkt(0, 55), new TidPunkt(01, 10), "tre", "lite längre text här\nNyrad\nhej\nkalas\nhkalas", false);
        cbc.addEvent(new TidPunkt(1, 05), new TidPunkt(01, 10), "tre", "lite längre text här\nNyrad\nhej\nkalas\nhkalas", false);
    }
    
    public static void main(String[] args) {
    	
//		testarCalenderKlassen tk = new testarCalenderKlassen();
		System.out.println("hej");
		
		Application.launch(args);
	}
}