package com.bookstore.view;

import java.net.URL;
import java.util.ResourceBundle;

import com.bookstore.entity.Book;
import com.bookstore.entity.Book.Category;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class BookStoreView implements Initializable {

	@FXML
	private TextField code;
	@FXML
	private TextField bookTitle;
	@FXML
	private TextField author;
	@FXML
	private ComboBox<Category> category;
	@FXML
	private TextField price;
	@FXML
	private TableView<Book> bookTable;
	@FXML
	private Label alert;
	@FXML
	private TextArea message;

	public void add() {

	}

	public void search() {

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		category.getItems().addAll(Category.values());
	}
}
