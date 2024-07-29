package com.green.domain;

import java.io.Serializable;

public class CartItem {
	private Book book;  // 도서
    private int quantity;  // 도서 개수
    private int totalPrice;  // 도서 가격

    //기본생성자
    public CartItem() {  
        // TODO Auto-generated constructor stub
    }  

    //일반생성자
    public CartItem(Book book) {  
        super();
        this.book = book;
        this.quantity = 1;
        this.totalPrice = book.getUnitPrice();
    } 

    //getter , setter 
    public Book getBook() {  
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
        this.updateTotalPrice();
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        this.updateTotalPrice();
    }

    public int getTotalPrice() {
        return totalPrice;
    }  

    public void updateTotalPrice() {  
        totalPrice = this.book.getUnitPrice()*this.quantity;
    }  

    //Source -> Generate hashCode() and equals() 를 선택한 다음
    //book 필드만 선택 후 Generate 하면 book에 대한 hashCode() 와 equals() 메서드가 자동 생성 됩니다.
    //hashCode() 와 equals()를 Override 하는 이유는 만약 같은 이름, 같은 값을 갖고 있는 도서 정보가 있을때
    //구분 하기 위해서 재정의 합니다.
    @Override
    public int hashCode() { 
        final int prime = 31;
        int result = 1;
        result = prime * result + ((book == null) ? 0 : book.hashCode());
        return result;
    }  

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CartItem other = (CartItem) obj;
        if (book == null) {
            if (other.book != null)
                return false;
        } else if (!book.equals(other.book))
            return false;
        return true;
    }  
}

