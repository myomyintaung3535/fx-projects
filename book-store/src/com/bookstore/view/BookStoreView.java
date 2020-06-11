package com.bookstore.view;

import java.net.URL;
import java.util.ResourceBundle;

import com.bookstore.entity.Book;
import com.bookstore.entity.Book.Category;
import com.bookstore.repo.BookStoreRepo;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;

public class BookStoreView implements Initializable {

	@FXML
	private VBox view;
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

	BookStoreRepo repo;

	public void add() {
		Book b = new Book();
		b.setCode(code.getText());
		b.setBookTitle(bookTitle.getText());
		b.setAuthor(author.getText());
		b.setCategory(category.getValue());
		b.setPrice(Integer.parseInt(price.getText()));

		repo.insert(b);
		refresh();
	}

	private void refresh() {
		bookTable.getItems().clear();
		bookTable.getItems().addAll(repo.searchAll());
		clear();
	}

	private void clear() {
		code.clear();
		bookTitle.clear();
		author.clear();
		category.getItems().clear();
		price.clear();
	}

	public void search() {

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		category.getItems().addAll(Category.values());
		repo = BookStoreRepo.getInstance();
		bookTable.getItems().addAll(repo.searchAll());
		view.setOnKeyPressed(e -> {
			if (e.getCode().equals(KeyCode.ENTER)) {
				add();
			}
		});

	}
}
