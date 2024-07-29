package com.green.domain;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

/*domain ��ü(DTO Ŭ����) �� ������ �� �̸� �ʿ��� �Ӽ�(�ʵ�)�� ���� �ϰ� �� �Ӽ��� setter�� getter�޼��带 ����� ��� ��. */
//** Book Ŭ������ Serializable Ŭ������ �����ϴ� ���� Ŭ������ ���Ӱ� ���� �մϴ�.
public class Book implements Serializable {

	// ** long Ÿ�� ��� serialVersionUID �� �߰� �մϴ�.
	// serialVersionUID �� ����ȭ(serialize) �Ҷ� ��Ÿ������ ����Ǵ� ID�Դϴ�.
	private static final long serialVersionUID = -7715651009026349175L;

	private String bookId;
	private String name;
	private int unitPrice; // ����
	private String author; // ����
	private String description; // ����
	private String publisher; // ���ǻ�
	private String category; // �з�
	private long unitsInStock; // ����
	private String releaseDate; // ������(��/��)
	private String condition; // �ű� ���� or �߰� ���� or ����å
	// �����̹��� �ʵ� �߰�
	private MultipartFile bookImage; // �����̹���
	// *** �����̸� �ʵ� �߰� ***
	private String fileName;

	/*
	 * �⺻ ������ �߰��ϱ� ���콺 ��Ŭ�� -> Source-> Generate Constructors from Superclass ����.
	 * object üũ Ȯ�� �� Generate.
	 */
	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}

	/*
	 * �Ϲ� ������ �߰��ϱ� ���콺 ��Ŭ�� ->Source->Generate Constructor using Fields ����. bookId,
	 * name, unitPrice �ʵ常 üũ�� �� Generate. Book Ŭ���� �� ����ϱ� ���� ��ü ������ ������ �Ű������� �Է� �ؾ�
	 * �մϴ�.
	 */
	public Book(String bookId, String name, int unitPrice) {
		super();
		this.bookId = bookId;
		this.name = name;
		this.unitPrice = unitPrice;
	}

	/*
	 * setter() �� getter() �޼��� �߰��ϱ� ���콺 ��Ŭ�� ->Source -> Generate Getter and Setter
	 * ����. ��� �ʵ� ���� �� Generate.
	 */

	// bookImage �ʵ� ���� getter, setter �߰�
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

	// ** fileName getter, setter �߰�
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
