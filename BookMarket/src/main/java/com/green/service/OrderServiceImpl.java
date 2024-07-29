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

	// confirmOrder() �޼���� ���� ��� ���� ���� ���� �ֹ� ���� ���θ� ó���մϴ�.
	// �ֹ� ���� ���� ��� ������ ������ IllegalArgumentException ���ܸ� �߻��մϴ�.
	public void confirmOrder(String bookId, long quantity) {

		Book bookById = bookRepository.getBookById(bookId);

		if (bookById.getUnitsInStock() < quantity) {
			throw new IllegalArgumentException("ǰ���Դϴ�. ��밡���� ����� :" + bookById.getUnitsInStock());

		}
		bookById.setUnitsInStock(bookById.getUnitsInStock() - quantity);
	}

	// saveOrder() �޼���� �ֹ� ������ ���� orderRepository�� saveOrder() �޼��带 ȣ���Ͽ�
	// �����ϰ� ���� ��ٱ��� ������ �������� �ֹ� ���� ID�� return �մϴ�.
	public Long saveOrder(Order order) {

		Long orderId = orderRepository.saveOrder(order);

		cartService.delete(order.getCart().getCartId());

		return orderId;
	}
}
