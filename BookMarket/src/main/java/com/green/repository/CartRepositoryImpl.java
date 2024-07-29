package com.green.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.green.domain.Cart;

@Repository
public class CartRepositoryImpl implements CartRepository {

	private Map<String, Cart> listOfCarts;

	public CartRepositoryImpl() {
		listOfCarts = new HashMap<String, Cart>();
	}

	// create() �޼���� ���ο� ��ٱ��� �� �����Ͽ� ��ٱ��� ID�� ����ϰ�, ������ ��ٱ��� ��ü�� return �մϴ�.
	// ������ ��ٱ��� ID�� �����ϸ� ���� ó���� ���� IllegalArgumentException() �޼��带 ȣ�� �մϴ�.
	public Cart create(Cart cart) {

		if (listOfCarts.keySet().contains(cart.getCartId())) {

			throw new IllegalArgumentException(String.format("��ٱ��ϸ� ������ �� �����ϴ�. ��ٱ��� id(%)�� �����մϴ�", cart.getCartId()));
		}

		listOfCarts.put(cart.getCartId(), cart);
		return cart;
	}

	// read() �޼���� ��ٱ���ID �� �Ű������� �޾Ƽ� ��ٱ��Ͽ� ��ϵ� ��� ������ return �մϴ�.
	public Cart read(String cartId) {
		return listOfCarts.get(cartId);
	}

	// ** update() �޼��� Override �߰� **
	public void update(String cartId, Cart cart) {
		if (!listOfCarts.keySet().contains(cartId)) {
			throw new IllegalArgumentException(String.format("��ٱ��� ����� ������ �� �����ϴ�. ��ٱ��� id(%)�� �������� �ʽ��ϴ�", cartId));
			// ��ٱ��ϰ� �������� ���� ��� ����ó��
		}
		listOfCarts.put(cartId, cart);
	}

	// ** delete() �޼��� Override �߰� **
	public void delete(String cartId) {
		if (!listOfCarts.keySet().contains(cartId)) {
			throw new IllegalArgumentException(String.format("��ٱ��� ����� ������ �� �����ϴ�. ��ٱ��� id(%)�� �������� �ʽ��ϴ�", cartId));
			// ��ٱ��� ID�� �������� ������ ���� ó��
		}
		listOfCarts.remove(cartId);
	}
}
