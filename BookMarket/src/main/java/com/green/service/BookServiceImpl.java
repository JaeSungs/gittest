package com.green.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.domain.Book;
import com.green.repository.BookRepository;

/* 서비스 계층은 프레젠테이션 계층(view) 과 퍼시스턴스 계층(메서드 정의된 Repository) 사이를 연결합니다.
 * @Service 로 정의 합니다. */
@Service
public class BookServiceImpl implements BookService{
	
	@Autowired //@Autowired 를 정의하여 메서드가 정의된 BookRepository 클래스의 getAllBookList() 메서드를 호출합니다.
	 private BookRepository bookRepository;
	 
	@Override
	 public List<Book> getAllBookList() { 
	        // TODO Auto-generated method stub
		 return bookRepository.getAllBookList();
	 } 
	 
	 @Override
	 public List<Book> getBookListByCategory(String category) {  
		 //BookRepository 클래스 getBookListByCategory() 메서드 호출
	      List<Book> booksByCategory = bookRepository.getBookListByCategory(category);  
	      
	      return booksByCategory;  
	 }  
	 
	 //BookRepository 클래스 getBookListByFilter() 메서드 호출
	 @Override
	 public Set<Book> getBookListByFilter(Map<String, List<String>> filter) {
	     Set<Book> booksByFilter = bookRepository.getBookListByFilter(filter); 
	      return booksByFilter;
	 }
	 
	 //BookRepository 클래스 getBookById() 메서드 호출
	 public Book getBookById(String bookId) {
	        Book bookById = bookRepository.getBookById(bookId);
	        return bookById;
	 }
	 
	 //BookRepository 클래스 setNewBook() 메서드 호출
	 public void setNewBook(Book book) {  
	        bookRepository.setNewBook(book);  
	 }  
}
