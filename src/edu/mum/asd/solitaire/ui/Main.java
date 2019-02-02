package edu.mum.asd.solitaire.ui;

import java.io.IOException;

import edu.mum.asd.framework.facade.CardGameFacade;
import edu.mum.asd.framework.singleton.CardGameFramework;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 */
public class Main extends Application {

	private Stage primaryStage;
	private BorderPane rootLayout;
	private CardGameFacade applicationFacade;
	private Canvas canvas;

	public Main() {
		this.applicationFacade = CardGameFramework.getGameInstance();
		canvas = new Canvas(800, 600);
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

			canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent t) {
					double x = t.getX();
					double y = t.getY();
					applicationFacade.moveCard(x, y);
					applicationFacade.repaint(canvas);

				}
			});

			applicationFacade.paintPiles(canvas);
			root.getChildren().add(canvas);
			rootLayout.setCenter(root);
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	public CardGameFacade getApplicationFacade() {
		return applicationFacade;
	}

	public Canvas getCanvas() {
		return canvas;
	}

}
