package com.green.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.domain.Book;
import com.green.domain.Order;
import com.green.repository.BookRepository;
import com.green.repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private CartService cartService;

	// confirmOrder() 메서드는 도서 재고 수에 대한 도서 주문 가능 여부를 처리합니다.
	// 주문 도서 수가 재고 수보다 많으면 IllegalArgumentException 예외를 발생합니다.
	public void confirmOrder(String bookId, long quantity) {

		Book bookById = bookRepository.getBookById(bookId);

		if (bookById.getUnitsInStock() < quantity) {
			throw new IllegalArgumentException("품절입니다. 사용가능한 제고수 :" + bookById.getUnitsInStock());

		}
		bookById.setUnitsInStock(bookById.getUnitsInStock() - quantity);
	}

	// saveOrder() 메서드는 주문 내역에 대해 orderRepository의 saveOrder() 메서드를 호출하여
	// 저장하고 현재 장바구니 정보를 삭제한후 주문 내역 ID를 return 합니다.
	public Long saveOrder(Order order) {

		Long orderId = orderRepository.saveOrder(order);

		cartService.delete(order.getCart().getCartId());

		return orderId;
	}
}
