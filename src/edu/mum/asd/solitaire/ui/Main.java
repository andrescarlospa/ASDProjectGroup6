package edu.mum.asd.solitaire.ui;

import java.io.IOException;

import edu.mum.asd.framework.Card;
import edu.mum.asd.framework.CardPile;
import edu.mum.asd.framework.DeckPile;
import edu.mum.asd.framework.DiscardPile;
import edu.mum.asd.framework.SuitPile;
import edu.mum.asd.framework.TablePile;
import edu.mum.asd.framework.game.ListIterator;
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
	        Canvas canvas = new Canvas(800, 800);
	        
	        canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, 
	                new EventHandler<MouseEvent>() {
	                    @Override
	                    public void handle(MouseEvent t) {            
	                        //if (t.getClickCount() >1) {
	                    	double x = t.getX();
	        				double y = t.getY();

	        				for (CardPile pile : application.getExternalizedState().getPiles())
	        					if (pile.includes(x, y)) {
	        						if(pile.getCards().empty() && pile instanceof DeckPile) {
	        							
	        							ListIterator iterator = application.getExternalizedState().getDiscard().getCards().iterator();

	        							// build reversed order list
	        							while (!iterator.atEnd()) {
	        								Card card = (Card) iterator.current();
	        								card.flip();
	        								pile.addCard(card);
	        								iterator.next();
	        							}
	        							((DiscardPile)application.getExternalizedState().getDiscard()).clear();
	        						}else if(pile instanceof DeckPile){
	        							Card card = (Card)pile.getCards().pop();
	        							card.flip();
	        							application.getExternalizedState().getDiscard().addCard(card);
	        						}else if(pile instanceof TablePile) {
	        							// if face down, then flip
	        							tablePileLogic(pile);
	        							
	        						}else if(pile instanceof DiscardPile) {
	        							dicardPileLogic(pile);
	        						}
	        						
	        						repaint(canvas);
	        						break;
	        					}
	                        	System.out.println(t.getX());
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
	
	public void dicardPileLogic(CardPile pile) {
		if (pile.getCards().empty())
			return;

		Card topCard = (Card)pile.getCards().front();

		// check the SuitPile's first
		for (int i = 0; i < SuitPile.numberSuits; i++)
			if (application.getExternalizedState().getSuits().get(i).canTake(topCard)) {
				application.getExternalizedState().getSuits().get(i).addCard((Card)pile.getCards().pop());
				return;
			}

		// then check the TablePile's
		
		for (int i = 0; i < TablePile.numberPiles; i++)
			if (application.getExternalizedState().getTablePiles().get(i).canTake(topCard)) {
				application.getExternalizedState().getTablePiles().get(i).addCard((Card)pile.getCards().pop());

				return;
			}
	}
	
	public void tablePileLogic(CardPile pile) {
		if (pile.getCards().empty())
			return;
		
		Card topCard = (Card)pile.getCards().front();
		if (!topCard.faceUp()) {
			topCard.flip();
		}else {
			
			
			for (int i = 0; i < SuitPile.numberSuits; i++)
				if (application.getExternalizedState().getSuits().get(i).canTake(topCard)) {
					application.getExternalizedState().getSuits().get(i).addCard((Card)pile.getCards().pop());
					return;
				}

			// try to create a build
			CardPile build = new SuitPile(0, 0);

			// get the cards for the build from the suit pile
			while (!pile.getCards().empty()) {
				// stop if we reached a card that is face down
				if (!((Card)pile.getCards().front()).faceUp())
					break;
				build.addCard(((Card)pile.getCards().pop()));
			}

			// We don't allow the user to play a King card
			// that is at the bottom of a table pile
			// to another table pile
			if (build.top().isKing() && pile.getCards().empty()) {
				while (!build.getCards().empty())
					pile.addCard((Card)build.getCards().pop());
				return;
			}

			// if we have to play only one card
			if (build.top() == topCard) {
				// put it back into the table pile
				pile.getCards().add(build.getCards().pop());

				// we have already tried the suit piles
				// see if any other table pile can take card
				for (int i = 0; i < TablePile.numberPiles; i++)
					if ( application.getExternalizedState().getTablePiles().get(i).canTake(topCard)) {
						application.getExternalizedState().getTablePiles().get(i).addCard((Card) pile.getCards().pop());
						return;
					}
			} else // we got ourselves a build to play
			{
				topCard = build.top();

				// see if any other table pile can take this build
				for (int i = 0; i < TablePile.numberPiles; i++)
					if (application.getExternalizedState().getTablePiles().get(i).canTake(topCard)) {
						while (!build.getCards().empty())
							application.getExternalizedState().getTablePiles().get(i).addCard((Card)build.getCards().pop());

						return;
					}

				// can't play the build?
				// then we must restore our pile
				while (!build.getCards().empty())
					pile.getCards().add((Card)build.getCards().pop());
			}
			
			
		}
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
		gc.clearRect(0, 0, 800, 800);
		gc.setFill(Color.GREEN);
        gc.setStroke(Color.BLUE);
        gc.setLineWidth(2);
		for (CardPile pile : application.getExternalizedState().getPiles())
			pile.display(gc);
	}

}
