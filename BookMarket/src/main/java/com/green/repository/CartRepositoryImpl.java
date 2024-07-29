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

	// create() 메서드는 새로운 장바구니 를 생성하여 장바구니 ID를 등록하고, 생성된 장바구니 객체를 return 합니다.
	// 동일한 장바구니 ID가 존재하면 예외 처리를 위해 IllegalArgumentException() 메서드를 호출 합니다.
	public Cart create(Cart cart) {

		if (listOfCarts.keySet().contains(cart.getCartId())) {

			throw new IllegalArgumentException(String.format("장바구니를 생성할 수 없습니다. 장바구니 id(%)가 존재합니다", cart.getCartId()));
		}

		listOfCarts.put(cart.getCartId(), cart);
		return cart;
	}

	// read() 메서드는 장바구니ID 를 매개변수로 받아서 장바구니에 등록된 모든 정보를 return 합니다.
	public Cart read(String cartId) {
		return listOfCarts.get(cartId);
	}

	// ** update() 메서드 Override 추가 **
	public void update(String cartId, Cart cart) {
		if (!listOfCarts.keySet().contains(cartId)) {
			throw new IllegalArgumentException(String.format("장바구니 목록을 갱신할 수 없습니다. 장바구니 id(%)가 존재하지 않습니다", cartId));
			// 장바구니가 존재하지 않을 경우 예외처리
		}
		listOfCarts.put(cartId, cart);
	}

	// ** delete() 메서드 Override 추가 **
	public void delete(String cartId) {
		if (!listOfCarts.keySet().contains(cartId)) {
			throw new IllegalArgumentException(String.format("장바구니 목록을 삭제할 수 없습니다. 장바구니 id(%)가 존재하지 않습니다", cartId));
			// 장바구니 ID가 존재하지 않으면 예외 처리
		}
		listOfCarts.remove(cartId);
	}
}
