package edu.mum.asd.solitaire.ui;

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
	public void handleAddNewLibraryMember() {
	}
	
	@FXML
	public void handleOverdue() {
	}
}
