package com.green.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.green.domain.Book;

public interface BookService {
	List<Book> getAllBookList();
	
	List<Book> getBookListByCategory(String category);
	
	//getBookListByFilter() �޼��� �߰�
	Set<Book> getBookListByFilter(Map<String, List<String>> filter);
	
	//getBookById() �޼��� �߰�
	Book getBookById(String bookId);
	
	//������� �޼��� �߰�
	void setNewBook(Book book);
}
