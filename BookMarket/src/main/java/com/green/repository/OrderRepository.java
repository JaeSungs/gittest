package com.green.repository;

import com.green.domain.Order;

public interface OrderRepository {
	
    Long saveOrder(Order order);
}
