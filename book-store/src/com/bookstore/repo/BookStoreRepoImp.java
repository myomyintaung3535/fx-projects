package com.bookstore.repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bookstore.entity.Book;
import com.bookstore.entity.Book.Category;
import com.bookstore.util.ConnectionManager;

public class BookStoreRepoImp implements BookStoreRepo {

	private static final String INSERT = "INSERT into bookstore (book_code,book_title,author,category,price) "
			+ " values (?,?,?,?,?)";
	private static final String SELECT = "SELECT * from bookstore";

	@Override
	public void insert(Book book) {
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
		List<Book> bookList = new ArrayList<>();

		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement stmt = conn.prepareStatement(SELECT)) {

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Book b = getData(rs);
				bookList.add(b);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return bookList;
	}

	private Book getData(ResultSet rs) throws SQLException {
		Book b = new Book();
		b.setCode(rs.getString(2));
		b.setBookTitle(rs.getString(3));
		b.setAuthor(rs.getString(4));
		b.setCategory(Category.valueOf(rs.getString(5)));
		b.setPrice(rs.getInt(6));

		return b;
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
