package edu.mum.asd.solitaire.ui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class BookCopyController {

	@FXML
	private TextField isbn;
	@FXML
	private TextField copyNumber;
	@FXML
	private Label lblBookAvailable;
	@FXML
	private Button btnAddCopy;
	@FXML
	private TableView<Object> bookCopyTable;
	@FXML
	private TableColumn<Object, String> copyNumberColumn;


	public BookCopyController() {
	}
	@FXML
	public void btnSearch(ActionEvent event) throws IOException {
		
	}

	@FXML
	public void btnAddCopy() {
		
		
	}

	private Main mainApp;

	public void setMainApp(Main main) {
		this.mainApp = main;
	}

}
