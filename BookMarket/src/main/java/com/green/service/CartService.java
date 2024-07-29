package com.green.service;

import com.green.domain.Cart;

public interface CartService {
	
    Cart create(Cart cart);
    
    Cart read(String cartId);
    //** update() �޼��� �߰� **
    void update(String cartId, Cart cart);
    //** delete() �޼��� �߰� **
    void delete(String cartId);
    //��ٱ����� ����ó�� ���� validateCart() �޼��� �߰�
    Cart validateCart(String cartId);
}
