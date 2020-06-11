package com.bookstore.repo;

import java.util.List;

import com.bookstore.entity.Book;
import com.bookstore.entity.Book.Category;

public interface BookStoreRepo {

	static BookStoreRepo getInstance() {
		return new BookStoreRepoImp();
	}
	void save(Book book);
	
	List<Book> searchAll();
	
	List<Book> search(String code,String bookTitle,String author, Category category);
	
	void delete(Book book);
	
	void update(Book book);
}
