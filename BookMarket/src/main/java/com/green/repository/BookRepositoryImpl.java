package com.green.repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.green.domain.Book;
import com.green.exception.BookIdException;

/*@Repository �� ����Ͽ� �۽ý��Ͻ� ����(DAOŬ����) �� BookRepositoryImpl Ŭ������ ���� �մϴ�.*/
@Repository
public class BookRepositoryImpl implements BookRepository {
	
	//** JDBC���ø� ����ϱ� ���� ����
	private JdbcTemplate template;
	
	//setJdbctemplate() �޼���� �����ͺ��̽� ������ ���� �ۼ��մϴ�.
	@Autowired  
	 public void setJdbctemplate(DataSource dataSource) {
	    this.template = new JdbcTemplate(dataSource);
	 }
	
	
	private List<Book> listOfBooks = new ArrayList<Book>();
	
        
	//** ������ȸ �ϴ� getAllBookList() �޼��� ���� **
    @Override
    public List<Book> getAllBookList() { 
    	
    	//���� ��ȸ ���� �ۼ�
    	String SQL = "SELECT * FROM book";  
        
    	List<Book> listOfBooks = template.query(SQL, new BookRowMapper());  
        
    	return listOfBooks;
    } 
    
   //** getBookListByCategory() �޼��� ���� **
    public List<Book> getBookListByCategory(String category) { 
        
    	List<Book> booksByCategory = new ArrayList<Book>(); 
	       /* for(int i =0 ; i<listOfBooks.size() ; i++) {
	            Book book = listOfBooks.get(i);  
	            if(category.equalsIgnoreCase(book.getCategory()))   
	                booksByCategory.add(book);  
	        }
	        */
	    String SQL = "SELECT * FROM book where b_category LIKE '%" + category + "%'";  
	        
	    booksByCategory = template.query(SQL, new BookRowMapper());  
	        
	    return booksByCategory;   
    } 
    
    //getBookListByFilter() �޼��� �������̵�
    //�� ���� ��û�� url�� ���� ���ǻ�(publisher), �����о�(category)�� ���� �ϰ� ������
    //������ �˻��Ͽ� �ش� ��������� ��ȯ �մϴ�.
    //** getBookListByFilter() �޼��� ���� **
    public Set<Book> getBookListByFilter(Map<String, List<String>> filter) {
        
    	Set<Book> booksByPublisher = new HashSet<Book>();
        
    	Set<Book> booksByCategory = new HashSet<Book>();

        Set<String> booksByFilter = filter.keySet();
        
        //booksByFilter �� publisher �� ���ԵǾ� ������ �Ʒ� �ݺ��� ����
        if (booksByFilter.contains("publisher")) {  
            for (int j = 0; j < filter.get("publisher").size(); j++) { 
                String publisherName = filter.get("publisher").get(j); 
                /* for (int i = 0; i < listOfBooks.size(); i++) {
                    Book book = listOfBooks.get(i); 

                    if (publisherName.equalsIgnoreCase(book.getPublisher()))
                        booksByPublisher.add(book);
                }
                */
            	 String SQL = "SELECT * FROM book where b_publisher LIKE '%" + publisherName + "%'";  
                 booksByPublisher.addAll(template.query(SQL, new BookRowMapper())); 
            }
        }  

        if (booksByFilter.contains("category")) {  
            for (int i = 0; i < filter.get("category").size(); i++) { 
                String category = filter.get("category").get(i); 
                /*  List<Book> list = getBookListByCategory(category); 
                booksByCategory.addAll(list); 
                */
            	String SQL = "SELECT * FROM book where b_category LIKE '%" + category + "%'";  
                booksByCategory.addAll(template.query(SQL, new BookRowMapper())); 
            }
        }  

        booksByCategory.retainAll(booksByPublisher);  
        return booksByCategory;
    }
    
    //** getBookById() �޼��� ���� **
    //getBookById() �޼��� �������̵�
    public Book getBookById(String bookId) {
    	
        Book bookInfo = null;
    
        String SQL = "SELECT count(*) FROM book where b_bookId=?"; 
        
        int rowCount = template.queryForObject(SQL, Integer.class, bookId);
        
        if (rowCount != 0) {
        	
        	SQL = "SELECT * FROM book where b_bookId=?";  
        	
        	bookInfo = template.queryForObject(SQL, new Object[] { bookId }, new BookRowMapper());  
        }
        if(bookInfo == null)  
        	throw new BookIdException(bookId);
        return bookInfo;
    	}	
    
    //** setNewBook() �޼��� ���� **
    //������ϸ޼��� �������̵�
    public void setNewBook(Book book) {  
    	
    	 String SQL = "INSERT INTO book (b_bookId, b_name, b_unitPrice, b_author, b_description, b_publisher, b_category, b_unitsInStock, b_releaseDate,b_condition, b_fileName) " + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

         template.update(SQL, book.getBookId(), book.getName(), book.getUnitPrice(), book.getAuthor(),
         book.getDescription(), book.getPublisher(), book.getCategory(), book.getUnitsInStock(),
         book.getReleaseDate(), book.getCondition(), book.getFileName());
         
         return; 
    } 
    
    //** �������� ���� �޼��� �������̵� **
    public void setUpdateBook(Book book) {  
    	
    	//���� �̹��� ���� �̸��� �������� b_fileName �ʵ� ����
        if (book.getFileName() != null) {
        	
            String SQL = "UPDATE Book SET b_name = ?, b_unitPrice = ?, b_author = ?, b_description = ?, b_publisher = ?, b_category = ?, b_unitsInStock = ?,b_releaseDate = ?, b_condition = ?, b_fileName = ?  where b_bookId = ? ";
           
            template.update(SQL, book.getName(), book.getUnitPrice(), book.getAuthor(), book.getDescription(), book.getPublisher(), book.getCategory(), book.getUnitsInStock(), book.getReleaseDate(), book.getCondition(), book.getFileName(), book.getBookId());
        
        //���� �̹��� ���� �̸��� �������� b_fileName �ʵ� ����
        } else if (book.getFileName() == null) {
        	
        	String SQL = "UPDATE Book SET b_name = ?, b_unitPrice = ?, b_author = ?, b_description = ?, b_publisher = ?, b_category = ?, b_unitsInStock = ?, b_releaseDate = ?, b_condition = ?  where b_bookId = ? ";
            
        	template.update(SQL, book.getName(), book.getUnitPrice(), book.getAuthor(), book.getDescription(), book.getPublisher(), book.getCategory(), book.getUnitsInStock(), book.getReleaseDate(), book.getCondition(), book.getBookId());
        }
    } 
}
