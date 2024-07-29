package com.green.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.domain.Cart;
import com.green.exception.CartException;
import com.green.repository.CartRepository;

@Service
public class CartServiceImpl implements CartService{

	//@Autowired �� �̿��ؼ� CartRepository Ŭ���� ��ü�� ��� �մϴ�.
    @Autowired
    private CartRepository cartRepository; 

    public Cart create(Cart cart) {  
        //CartRepository �� create() �޼��� ȣ�� �Ͽ� return
    	return cartRepository.create(cart);
    }  

    public Cart read(String cartId) {  
    	//CartRepository �� read() �޼��� ȣ�� �Ͽ� return
    	return cartRepository.read(cartId);
    }  
    //** update() �޼��� �߰� **
    public void update(String cartId, Cart cart) {
        cartRepository.update(cartId, cart);
    }
    //** delete() �޼��� �߰� **
    public void delete(String cartId) {
        cartRepository.delete(cartId);
    }
    
    //validateCart() �޼��� �������̵�
    public Cart validateCart(String cartId) {  
        
    	Cart cart = cartRepository.read(cartId);
        
        if(cart==null || cart.getCartItems().size()==0) {
            throw new CartException(cartId);
        } 
        
        return cart;
    } 

}

