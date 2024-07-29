package com.green.repository;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.green.domain.Book;

/* ��ӹ޾� �����ϱ� ���ؼ� interface Ŭ���� �� BookRepository �� �����ϰ�
 * getAllBookList() �߻�޼��带 �����մϴ�. */
public interface BookRepository {
	
	List<Book> getAllBookList();
	
	List<Book> getBookListByCategory(String category);
	
	//getBookListByFilter() �޼��� �߰�
	Set<Book> getBookListByFilter(Map<String, List<String>> filter);
	
	//getBookById() �޼��� �߰�
	Book getBookById(String bookId);
	
	//������� �޼��� �߰�
	void setNewBook(Book book);
	
	//** �������� ���� �޼��� �߰� **
	void setUpdateBook(Book book);
}
