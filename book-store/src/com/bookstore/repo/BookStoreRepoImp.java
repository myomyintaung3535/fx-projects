package com.bookstore.repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import com.bookstore.entity.Book;
import com.bookstore.entity.Book.Category;
import com.bookstore.util.ConnectionManager;

public class BookStoreRepoImp implements BookStoreRepo {

	private static final String INSERT = "INSERT into bookstore (book_code,book_title,author,category,price) "
			+ " values (?,?,?,?,?)";

	@Override
	public void save(Book book) {
		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement stmt = conn.prepareStatement(INSERT)) {
			stmt.setString(1, book.getCode());
			stmt.setString(2, book.getBookTitle());
			stmt.setString(3, book.getAuthor());
			stmt.setString(4, String.valueOf(book.getCategory()));
			stmt.setInt(5, book.getPrice());
			stmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<Book> searchAll() {
		return null;
	}

	@Override
	public List<Book> search(String code, String bookTitle, String author, Category category) {
		return null;
	}

	@Override
	public void delete(Book book) {

	}

	@Override
	public void update(Book book) {

	}

}
