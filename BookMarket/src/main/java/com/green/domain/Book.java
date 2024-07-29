package com.green.domain;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

/*domain 객체(DTO 클래스) 는 데이터 모델 이며 필요한 속성(필드)를 정의 하고 각 속성에 setter와 getter메서드를 만들어 줘야 함. */
//** Book 클래스를 Serializable 클래스를 구현하는 구현 클래스로 새롭게 정의 합니다.
public class Book implements Serializable {

	// ** long 타입 상수 serialVersionUID 를 추가 합니다.
	// serialVersionUID 는 직렬화(serialize) 할때 메타정보로 저장되는 ID입니다.
	private static final long serialVersionUID = -7715651009026349175L;

	private String bookId;
	private String name;
	private int unitPrice; // 가격
	private String author; // 저자
	private String description; // 설명
	private String publisher; // 출판사
	private String category; // 분류
	private long unitsInStock; // 재고수
	private String releaseDate; // 출판일(월/년)
	private String condition; // 신규 도서 or 중고 도서 or 전자책
	// 도서이미지 필드 추가
	private MultipartFile bookImage; // 도서이미지
	// *** 파일이름 필드 추가 ***
	private String fileName;

	/*
	 * 기본 생성자 추가하기 마우스 우클릭 -> Source-> Generate Constructors from Superclass 선택.
	 * object 체크 확인 후 Generate.
	 */
	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}

	/*
	 * 일반 생성자 추가하기 마우스 우클릭 ->Source->Generate Constructor using Fields 선택. bookId,
	 * name, unitPrice 필드만 체크한 후 Generate. Book 클래스 를 사용하기 위해 객체 생성시 세가지 매개변수를 입력 해야
	 * 합니다.
	 */
	public Book(String bookId, String name, int unitPrice) {
		super();
		this.bookId = bookId;
		this.name = name;
		this.unitPrice = unitPrice;
	}

	/*
	 * setter() 와 getter() 메서드 추가하기 마우스 우클릭 ->Source -> Generate Getter and Setter
	 * 선택. 모든 필드 선택 후 Generate.
	 */

	// bookImage 필드 관련 getter, setter 추가
	public MultipartFile getBookImage() {
		return bookImage;
	}

	public void setBookImage(MultipartFile bookImage) {
		this.bookImage = bookImage;
	}

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(int unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public long getUnitsInStock() {
		return unitsInStock;
	}

	public void setUnitsInStock(long unitsInStock) {
		this.unitsInStock = unitsInStock;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	// ** fileName getter, setter 추가
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
