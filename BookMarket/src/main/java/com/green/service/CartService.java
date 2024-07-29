package com.green.service;

import com.green.domain.Cart;

public interface CartService {
	
    Cart create(Cart cart);
    
    Cart read(String cartId);
    //** update() 메서드 추가 **
    void update(String cartId, Cart cart);
    //** delete() 메서드 추가 **
    void delete(String cartId);
    //장바구니의 예외처리 위한 validateCart() 메서드 추가
    Cart validateCart(String cartId);
}
