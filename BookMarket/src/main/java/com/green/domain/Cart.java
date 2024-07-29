package com.green.domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Cart{
	
    private String cartId;  // 장바구니 ID
    private Map<String,CartItem> cartItems; // 장바구니 항목
    private int grandTotal;  // 총액

    //기본생성자
    public Cart() {  
        cartItems = new HashMap<String, CartItem>();
        grandTotal = 0;
    }  

    //일반생성자
    public Cart(String cartId) {  
        this();
        this.cartId = cartId;
    }  

    //getter, setter
    public String getCartId() {  
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public Map<String, CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(Map<String, CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public int getGrandTotal() {
        return grandTotal;
    }  

    //updateGrandTotal() 메서드는 장바구니에 등록되는 도서가격의 총합을 계산합니다.
    public void updateGrandTotal() { 
    	
        grandTotal= 0;
        
        for(CartItem item : cartItems.values()){
            grandTotal = grandTotal + item.getTotalPrice();
        }
    }  
    
    //** 도서 목록중 선택한 도서를 장바구니에 등록하는 addCartItem() 메서드 추가 **
    public void addCartItem(CartItem item) {
        String bookId = item.getBook().getBookId(); //현재 등록하기 위한 도서 ID 가져오기

        //도서ID가 cartItems 객체에 등록 되어있는지 확인
        if(cartItems.containsKey(bookId)) {  
        	//등록된 도서 ID에 대한 정보 가져오기
            CartItem cartItem = cartItems.get(bookId);  
            //등록된 도서 ID의 개수 추가 저장
            cartItem.setQuantity(cartItem.getQuantity()+item.getQuantity());  
            //등록된 도서 ID에 대한 변경 정보(cartItem) 저장
            cartItems.put(bookId, cartItem);  
        } else {
        	//도서ID에 대한 도서 정보 저장
            cartItems.put(bookId, item);  
        }
        updateGrandTotal(); //총액 계산하기  
    }
    
    //** 등록된 도서 항목 삭제하는 removeCartItem() 메서드 추가 **
    public void removeCartItem(CartItem item) {
        
    	String bookId = item.getBook().getBookId();
        
    	cartItems.remove(bookId); //bookId 도서 삭제  
        
    	updateGrandTotal(); //총액 갱신  
    }
    
    //cartId 필드의 hashCode() 메서드, equals() 자동생성
    @Override  
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((cartId == null) ? 0 : cartId.hashCode());
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
        Cart other = (Cart) obj;
        if (cartId == null) {
            if (other.cartId != null)
                return false;
        } else if (!cartId.equals(other.cartId))
            return false;
        return true;
    }  
}
