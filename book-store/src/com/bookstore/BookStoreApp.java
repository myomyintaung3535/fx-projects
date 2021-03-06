package com.bookstore;

import com.bookstore.view.BookStoreView;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BookStoreApp extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(BookStoreView.class.getResource("BookStoreView.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		System.out.println("");
	}
	

	public static void main(String[] args) {
		launch(args);
	}

}
