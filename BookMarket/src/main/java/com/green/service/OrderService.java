package com.green.service;

import com.green.domain.Order;

public interface OrderService {
	
    void confirmOrder(String  bookId, long quantity);
    
    Long saveOrder(Order order);
}
