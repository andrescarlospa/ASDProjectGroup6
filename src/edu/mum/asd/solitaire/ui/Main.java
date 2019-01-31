package edu.mum.asd.solitaire.ui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 */
public class Main extends Application {

	private Stage primaryStage;
	private BorderPane rootLayout;

	public Main() {
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("AddressApp");
		this.primaryStage.getIcons().add(new Image("file:resources/images/icon.png"));
		initRootLayout();
		showMenu();
	}

	public void initRootLayout() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();
			RootLayoutController controller = loader.getController();
			controller.setMainApp(this);
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

	public void showBookCopy() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("BookCopy.fxml"));
			AnchorPane bookOverview = (AnchorPane) loader.load();
			rootLayout.setCenter(bookOverview);
			primaryStage.setTitle("");
			BookCopyController controller = loader.getController();
			controller.setMainApp(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
