package com.green.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.green.domain.Order;

@Repository
public class OrderRepositoryImpl implements OrderRepository{
	
    private Map<Long, Order> listOfOrders;
    
    private long nextOrderId;

    public OrderRepositoryImpl() {
        listOfOrders = new HashMap<Long, Order>();
        nextOrderId = 2000;
    }
    //saveOrder() 메서드는 주문 내역에 대한 ID와 주문 내역 등을 저장하고 주문 내역ID를 반환합니다.
    public Long saveOrder(Order order) { 
    	
        order.setOrderId(getNextOrderId());
        
        listOfOrders.put(order.getOrderId(), order);
        
        return order.getOrderId();
    }  
    
    private synchronized long getNextOrderId() {
        return nextOrderId++;
    }
}