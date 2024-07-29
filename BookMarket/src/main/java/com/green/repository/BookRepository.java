package com.green.repository;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.green.domain.Book;

/* 상속받아 구현하기 위해서 interface 클래스 인 BookRepository 를 생성하고
 * getAllBookList() 추상메서드를 생성합니다. */
public interface BookRepository {
	
	List<Book> getAllBookList();
	
	List<Book> getBookListByCategory(String category);
	
	//getBookListByFilter() 메서드 추가
	Set<Book> getBookListByFilter(Map<String, List<String>> filter);
	
	//getBookById() 메서드 추가
	Book getBookById(String bookId);
	
	//도서등록 메서드 추가
	void setNewBook(Book book);
	
	//** 도서정보 수정 메서드 추가 **
	void setUpdateBook(Book book);
}
