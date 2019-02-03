package edu.mum.asd.solitaire.ui;

import edu.mum.asd.framework.singleton.CardGameFramework;
import edu.mum.asd.framework.strategy.SolitaireStrategy;
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
		//mainApp.showMenu();
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
	public void handleNewGame() {
		CardGameFramework.getGameInstance().getExternalizedState().initialization();
		mainApp.getApplicationFacade().repaint(mainApp.getCanvas());
	}

	@FXML
	public void handleSaveGame() {
		try {
			CardGameFramework.getGameInstance().saveToMemento();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void handleUndo() {
		CardGameFramework.getGameInstance().restoreFromMemento();
		mainApp.getApplicationFacade().repaint(mainApp.getCanvas());
	}
}
