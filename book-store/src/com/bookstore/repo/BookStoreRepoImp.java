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
			+ " values (?,?,?,?,?) ";
	private static final String SELECT = "SELECT * from bookstore where 1 = 1 ";
	private static final String DELETE = "DELETE from bookstore where id = ?";
	private static final String UPDATE = "UPDATE bookstore set book_code = ? , book_title = ? , author = ? , category = ?, "
			+ " price = ? where id = ? ";

	@Override
	public void insertAndUpdate(Book book) {
		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement stmt = conn.prepareStatement(book.getId() > 0 ? UPDATE : INSERT)) {
			stmt.setString(1, book.getCode());
			stmt.setString(2, book.getBookTitle());
			stmt.setString(3, book.getAuthor());
			stmt.setString(4, String.valueOf(book.getCategory()));
			stmt.setInt(5, book.getPrice());
			if (book.getId() > 0) {
				stmt.setInt(6, book.getId());
			}
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
		b.setId(rs.getInt(1));
		b.setCode(rs.getString(2));
		b.setBookTitle(rs.getString(3));
		b.setAuthor(rs.getString(4));
		b.setCategory(Category.valueOf(rs.getString(5)));
		b.setPrice(rs.getInt(6));
		return b;
	}

	@Override
	public List<Book> search(String code, String bookTitle, String author, Category category) {
		StringBuilder sb = new StringBuilder(SELECT);
		List<Book> bookList = new ArrayList<>();
		List<Object> params = new ArrayList<>();

		if (checkStringNullAndEmpty(code)) {
			sb.append(" and book_code regexp ? ");
			params.add(code);
		}
		if (checkStringNullAndEmpty(bookTitle)) {
			sb.append(" and book_title regexp ? ");
			params.add(bookTitle);
		}
		if (checkStringNullAndEmpty(author)) {
			sb.append(" and author regexp ? ");
			params.add(author);
		}
		if (category != null) {
			sb.append(" and category regexp ? ");
			params.add(category);
		}

		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sb.toString())) {

			for (int i = 0; i < params.size(); i++) {
				Object obj = params.get(i);
				if (obj instanceof Category) {
					stmt.setString(i + 1, obj.toString());
				} else {
					stmt.setObject(i + 1, obj);
				}

			}
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

	private boolean checkStringNullAndEmpty(String str) {
		return (str != null && !str.isEmpty()) ? true : false;
	}

	@Override
	public void delete(Book book) {
		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement stmt = conn.prepareStatement(DELETE)) {
			stmt.setInt(1, book.getId());
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
