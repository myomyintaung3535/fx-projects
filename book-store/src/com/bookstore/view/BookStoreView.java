package com.bookstore.view;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.bookstore.entity.Book;
import com.bookstore.entity.Book.Category;
import com.bookstore.repo.BookStoreRepo;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
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
	private BookStoreRepo repo;
	private Book book;
	int i = 0;

	public void add() {
		book = book.getId() > 0 ? book : new Book();
		book.setCode(code.getText());
		book.setBookTitle(bookTitle.getText());
		book.setAuthor(author.getText());
		book.setCategory(category.getValue());
		book.setPrice(Integer.parseInt(price.getText()));
		repo.insertAndUpdate(book);
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
		setCategoryComboBox();
		price.clear();
	}

	private void setCategoryComboBox() {
		category.getItems().clear();
		category.setPromptText("Test");
		System.out.println("invoke set method " + (i++));
		category.getItems().addAll(Category.values());

	}

	public void search() {
		List<Book> books = repo.search(code.getText(), bookTitle.getText(), author.getText(), category.getValue());
		bookTable.getItems().clear();
		bookTable.getItems().addAll(books);
		clear();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setCategoryComboBox();
		repo = BookStoreRepo.getInstance();
		bookTable.getItems().addAll(repo.searchAll());
		view.setOnKeyPressed(e -> {
			if (e.getCode().equals(KeyCode.ENTER)) {
				add();
			}
		});

		MenuItem update = new MenuItem("update");
		MenuItem delete = new MenuItem("delete");
		bookTable.setContextMenu(new ContextMenu(update, delete));

		bookTable.setOnMouseClicked(event -> {
			update.setOnAction(e -> {
				book = bookTable.getSelectionModel().getSelectedItem();
				setBookView(book);
			});

			delete.setOnAction(e -> {
				bookTable.getSelectionModel().getSelectedItems().forEach(repo::delete);
				refresh();
			});
		});

	}

	private void setBookView(Book b) {
		System.out.println(b.getAuthor());
		code.setText(b.getCode());
		bookTitle.setText(b.getBookTitle());
		author.setText(b.getAuthor());
		category.setValue(b.getCategory());
		price.setText(String.valueOf(b.getPrice()));
	}
}
