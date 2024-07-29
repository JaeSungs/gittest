package com.green.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.green.domain.Book;

public interface BookService {
	List<Book> getAllBookList();
	
	List<Book> getBookListByCategory(String category);
	
	//getBookListByFilter() 메서드 추가
	Set<Book> getBookListByFilter(Map<String, List<String>> filter);
	
	//getBookById() 메서드 추가
	Book getBookById(String bookId);
	
	//도서등록 메서드 추가
	void setNewBook(Book book);
}
