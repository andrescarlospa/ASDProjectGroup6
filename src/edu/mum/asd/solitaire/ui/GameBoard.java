package edu.mum.asd.solitaire.ui;

import java.util.HashMap;

import javax.management.relation.Role;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class GameBoard {

	private Main mainApp;

	@FXML
	private Button addNewBookBtn;

	@FXML
	private Button addNewMemberBtn;

	@FXML
	private Button checkoutBookBtn;

	public void setMainApp(Main main) {
		this.mainApp = main;
	}

	public void setFuntionList (HashMap<String, Role> roles) {

	}

	@FXML
	private void initialize() {

	}


	@FXML
	private void handleChekoutBook() {

	}

	@FXML
	public void handleBookCopy() {
		mainApp.showBookCopy();
	}

	@FXML
	public void handleAddNewBook() {

	}

	@FXML
	public void handleAddNewLibraryMember() {

	}
	
	@FXML
	public void handleOverdue() {
		//mainApp.showOverdue();
	}
	
}

