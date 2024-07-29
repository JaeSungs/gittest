package com.green.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.domain.Book;
import com.green.repository.BookRepository;

/* ���� ������ ���������̼� ����(view) �� �۽ý��Ͻ� ����(�޼��� ���ǵ� Repository) ���̸� �����մϴ�.
 * @Service �� ���� �մϴ�. */
@Service
public class BookServiceImpl implements BookService{
	
	@Autowired //@Autowired �� �����Ͽ� �޼��尡 ���ǵ� BookRepository Ŭ������ getAllBookList() �޼��带 ȣ���մϴ�.
	 private BookRepository bookRepository;
	 
	@Override
	 public List<Book> getAllBookList() { 
	        // TODO Auto-generated method stub
		 return bookRepository.getAllBookList();
	 } 
	 
	 @Override
	 public List<Book> getBookListByCategory(String category) {  
		 //BookRepository Ŭ���� getBookListByCategory() �޼��� ȣ��
	      List<Book> booksByCategory = bookRepository.getBookListByCategory(category);  
	      
	      return booksByCategory;  
	 }  
	 
	 //BookRepository Ŭ���� getBookListByFilter() �޼��� ȣ��
	 @Override
	 public Set<Book> getBookListByFilter(Map<String, List<String>> filter) {
	     Set<Book> booksByFilter = bookRepository.getBookListByFilter(filter); 
	      return booksByFilter;
	 }
	 
	 //BookRepository Ŭ���� getBookById() �޼��� ȣ��
	 public Book getBookById(String bookId) {
	        Book bookById = bookRepository.getBookById(bookId);
	        return bookById;
	 }
	 
	 //BookRepository Ŭ���� setNewBook() �޼��� ȣ��
	 public void setNewBook(Book book) {  
	        bookRepository.setNewBook(book);  
	 }  
}
