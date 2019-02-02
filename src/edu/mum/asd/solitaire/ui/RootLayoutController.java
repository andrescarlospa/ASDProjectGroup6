package edu.mum.asd.solitaire.ui;

import edu.mum.asd.framework.GameBoard;
import edu.mum.asd.framework.singleton.Application;
import javafx.fxml.FXML;

public class RootLayoutController {

	@FXML
	private void initialize() {
		preJava8();
	}

	private void preJava8() {

	}

	@FXML
	public void back() {
		mainApp.showMenu();
	}

	private Main mainApp;

	public void setMainApp(Main main) {
		this.mainApp = main;
	}

	@FXML
	private void handleChekoutBook() {
	}

	@FXML
	public void handleBookCopy() {
	}

	@FXML
	public void handleAddNewBook() {
	}

	@FXML
	public void handleSaveGame() {
		System.out.println("Saving...");
		try {
			Application.getGameInstance().saveToMemento();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@FXML
	public void handleUndo() {
		System.out.println("Saving...");
		Application.getGameInstance().restoreFromMemento();
		mainApp.repaint();
	}
}
