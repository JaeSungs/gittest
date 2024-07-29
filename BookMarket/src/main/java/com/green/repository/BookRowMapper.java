package com.green.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.green.domain.Book;

public class BookRowMapper implements RowMapper<Book> {

	public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
		// 데이터 베이스에 등록된 도서 목록을 Book 객체에 담기위해 설정
		Book book = new Book();
		// 쿼리 실행한 결과 값을 domain 클래스 Book의 필드 변수에 setter 메서드를 이용해 담습니다.
		book.setBookId(rs.getString(1));
		book.setName(rs.getString(2));
		book.setUnitPrice(rs.getInt(3));
		book.setAuthor(rs.getString(4));
		book.setDescription(rs.getString(5));
		book.setPublisher(rs.getString(6));
		book.setCategory(rs.getString(7));
		book.setUnitsInStock(rs.getLong(8));
		book.setReleaseDate(rs.getString(9));
		book.setCondition(rs.getString(10));
		book.setFileName(rs.getString(11));

		return book;
	}
}