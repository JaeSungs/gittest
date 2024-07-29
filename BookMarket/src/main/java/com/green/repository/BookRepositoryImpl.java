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

/*@Repository 를 사용하여 퍼시스턴스 계층(DAO클래스) 인 BookRepositoryImpl 클래스를 정의 합니다.*/
@Repository
public class BookRepositoryImpl implements BookRepository {
	
	//** JDBC템플릿 사용하기 위한 설정
	private JdbcTemplate template;
	
	//setJdbctemplate() 메서드는 데이터베이스 연동을 위해 작성합니다.
	@Autowired  
	 public void setJdbctemplate(DataSource dataSource) {
	    this.template = new JdbcTemplate(dataSource);
	 }
	
	
	private List<Book> listOfBooks = new ArrayList<Book>();
	
        
	//** 도서조회 하는 getAllBookList() 메서드 수정 **
    @Override
    public List<Book> getAllBookList() { 
    	
    	//도서 조회 쿼리 작성
    	String SQL = "SELECT * FROM book";  
        
    	List<Book> listOfBooks = template.query(SQL, new BookRowMapper());  
        
    	return listOfBooks;
    } 
    
   //** getBookListByCategory() 메서드 수정 **
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
    
    //getBookListByFilter() 메서드 오버라이드
    //웹 에서 요청된 url에 도서 출판사(publisher), 도서분야(category)를 포함 하고 있으면
    //도서를 검색하여 해당 도서목록을 반환 합니다.
    //** getBookListByFilter() 메서드 수정 **
    public Set<Book> getBookListByFilter(Map<String, List<String>> filter) {
        
    	Set<Book> booksByPublisher = new HashSet<Book>();
        
    	Set<Book> booksByCategory = new HashSet<Book>();

        Set<String> booksByFilter = filter.keySet();
        
        //booksByFilter 에 publisher 가 포함되어 있으면 아래 반복문 실행
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
    
    //** getBookById() 메서드 수정 **
    //getBookById() 메서드 오버라이드
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
    
    //** setNewBook() 메서드 수정 **
    //도서등록메서드 오버라이드
    public void setNewBook(Book book) {  
    	
    	 String SQL = "INSERT INTO book (b_bookId, b_name, b_unitPrice, b_author, b_description, b_publisher, b_category, b_unitsInStock, b_releaseDate,b_condition, b_fileName) " + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

         template.update(SQL, book.getBookId(), book.getName(), book.getUnitPrice(), book.getAuthor(),
         book.getDescription(), book.getPublisher(), book.getCategory(), book.getUnitsInStock(),
         book.getReleaseDate(), book.getCondition(), book.getFileName());
         
         return; 
    } 
    
    //** 도서정보 수정 메서드 오버라이드 **
    public void setUpdateBook(Book book) {  
    	
    	//도서 이미지 파일 이름이 있을때는 b_fileName 필드 포함
        if (book.getFileName() != null) {
        	
            String SQL = "UPDATE Book SET b_name = ?, b_unitPrice = ?, b_author = ?, b_description = ?, b_publisher = ?, b_category = ?, b_unitsInStock = ?,b_releaseDate = ?, b_condition = ?, b_fileName = ?  where b_bookId = ? ";
           
            template.update(SQL, book.getName(), book.getUnitPrice(), book.getAuthor(), book.getDescription(), book.getPublisher(), book.getCategory(), book.getUnitsInStock(), book.getReleaseDate(), book.getCondition(), book.getFileName(), book.getBookId());
        
        //도서 이미지 파일 이름이 없을때는 b_fileName 필드 제외
        } else if (book.getFileName() == null) {
        	
        	String SQL = "UPDATE Book SET b_name = ?, b_unitPrice = ?, b_author = ?, b_description = ?, b_publisher = ?, b_category = ?, b_unitsInStock = ?, b_releaseDate = ?, b_condition = ?  where b_bookId = ? ";
            
        	template.update(SQL, book.getName(), book.getUnitPrice(), book.getAuthor(), book.getDescription(), book.getPublisher(), book.getCategory(), book.getUnitsInStock(), book.getReleaseDate(), book.getCondition(), book.getBookId());
        }
    } 
}
