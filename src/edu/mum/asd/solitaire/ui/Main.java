package edu.mum.asd.solitaire.ui;

import java.io.IOException;

import edu.mum.asd.framework.Card;
import edu.mum.asd.framework.CardPile;
import edu.mum.asd.framework.DeckPile;
import edu.mum.asd.framework.DiscardPile;
import edu.mum.asd.framework.game.Solitaire;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 */
public class Main extends Application {

	private Stage primaryStage;
	private BorderPane rootLayout;
	private edu.mum.asd.framework.singleton.Application application;

	public Main() {
		this.application = edu.mum.asd.framework.singleton.Application.getGameInstance();
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Solitaire");
		this.primaryStage.getIcons().add(new Image("file:resources/images/icon.png"));
		initRootLayout();
	}

	public void initRootLayout() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();
			RootLayoutController controller = loader.getController();
			controller.setMainApp(this);
			
			
			Group root = new Group();
	        Canvas canvas = new Canvas(800, 600);
	        canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, 
	                new EventHandler<MouseEvent>() {
	                    @Override
	                    public void handle(MouseEvent t) {            
	                        //if (t.getClickCount() >1) {
	                    	double x = t.getX();
	        				double y = t.getY();

	        				for (CardPile pile : application.getExternalizedState().getPiles())
	        					if (pile.includes(x, y)) {
	        						if(pile.getCards().isEmpty() && pile instanceof DeckPile) {
	        							application.getExternalizedState().getDiscard().getCards().stream()
	        							.forEach(card->{
	        								pile.addCard(card);
	        							});
	        							((DiscardPile)application.getExternalizedState().getDiscard()).clear();
	        							Card top = pile.getCards().get(0);
	        							top.flip();
	        						}else if(pile instanceof DeckPile){
	        							pile.select(x, y);
	        							Card card = pile.getCards().remove(0);
	        							card.flip();
	        							application.getExternalizedState().getDiscard().addCard(card);
	        						}
	        						repaint(canvas);
	        					}
	                        	System.out.println(t.getX());
	                            //reset(canvas, Color.BLUE);
	                        //}  
	                    }
	                });
	        
	        GraphicsContext gc = canvas.getGraphicsContext2D();
	        paintPiles(gc);
	        root.getChildren().add(canvas);
	        rootLayout.setCenter(root);
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void drawShapes(GraphicsContext gc) {
        gc.setFill(Color.GREEN);
        
        gc.setStroke(Color.BLUE);
        gc.setLineWidth(2);
        //gc.strokeLine(40, 10, 10, 40);
        //gc.fillOval(10, 60, 30, 30);
        //gc.strokeOval(60, 60, 30, 30);
        //gc.fillRoundRect(110, 60, 30, 30, 10, 10);
        gc.fillRect(160, 60, 50, 70);
        //gc.fillArc(10, 110, 30, 30, 45, 240, ArcType.OPEN);
        //gc.fillArc(60, 110, 30, 30, 45, 240, ArcType.CHORD);
        //gc.fillArc(110, 110, 30, 30, 45, 240, ArcType.ROUND);
        //gc.strokeArc(10, 160, 30, 30, 45, 240, ArcType.OPEN);
        //gc.strokeArc(60, 160, 30, 30, 45, 240, ArcType.CHORD);
        //gc.strokeArc(110, 160, 30, 30, 45, 240, ArcType.ROUND);
        //gc.fillPolygon(new double[]{10, 40, 10, 40},
        //               new double[]{210, 210, 240, 240}, 4);
        //gc.strokePolygon(new double[]{60, 90, 60, 90},
        //                 new double[]{210, 210, 240, 240}, 4);
        //gc.strokePolyline(new double[]{110, 140, 110, 140},
        //                  new double[]{210, 210, 240, 240}, 4);
    }

	public static void main(String[] args) {
		launch(args);
	}

	public void showMenu() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("GameBoard.fxml"));
			AnchorPane menu = (AnchorPane) loader.load();
			
			rootLayout.setCenter(menu);
			primaryStage.setTitle("");
			GameBoard menuController = loader.getController();
			menuController.setMainApp(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void repaint(Canvas canvas){
		GraphicsContext gc = canvas.getGraphicsContext2D();
		paintPiles(gc);
	}
	
	public void paintPiles(GraphicsContext gc) {
		gc.setFill(Color.GREEN);
        gc.setStroke(Color.BLUE);
        gc.setLineWidth(2);
		for (CardPile pile : application.getExternalizedState().getPiles())
			pile.display(gc);
	}

}
