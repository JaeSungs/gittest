package com.green.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.domain.Cart;
import com.green.exception.CartException;
import com.green.repository.CartRepository;

@Service
public class CartServiceImpl implements CartService{

	//@Autowired 를 이용해서 CartRepository 클래스 객체를 등록 합니다.
    @Autowired
    private CartRepository cartRepository; 

    public Cart create(Cart cart) {  
        //CartRepository 의 create() 메서드 호출 하여 return
    	return cartRepository.create(cart);
    }  

    public Cart read(String cartId) {  
    	//CartRepository 의 read() 메서드 호출 하여 return
    	return cartRepository.read(cartId);
    }  
    //** update() 메서드 추가 **
    public void update(String cartId, Cart cart) {
        cartRepository.update(cartId, cart);
    }
    //** delete() 메서드 추가 **
    public void delete(String cartId) {
        cartRepository.delete(cartId);
    }
    
    //validateCart() 메서드 오버라이드
    public Cart validateCart(String cartId) {  
        
    	Cart cart = cartRepository.read(cartId);
        
        if(cart==null || cart.getCartItems().size()==0) {
            throw new CartException(cartId);
        } 
        
        return cart;
    } 

}

