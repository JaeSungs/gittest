package com.green.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.green.domain.Book;
import com.green.domain.Cart;
import com.green.domain.CartItem;
import com.green.exception.BookIdException;
import com.green.service.BookService;
import com.green.service.CartService;

@Controller
@RequestMapping(value = "/cart")
public class CartController {

	// @Autowired �� �̿��ؼ� CartService Ŭ���� ��ü�� ��� �մϴ�.
	@Autowired
	private CartService cartService;

	// ** BookService Ŭ���� @Autowired �߰� **
	@Autowired
	private BookService bookService;

	// requestCartId() �޼���� /cart �� url ��û�� ������ ���ǿ� ����� id����
	// �����ͼ� /cart/ �� sessionid �� �߰��Ͽ� �����̷��� �մϴ�.
	@GetMapping
	public String requestCartId(HttpServletRequest request) {
		String sessionid = request.getSession(true).getId();
		return "redirect:/cart/" + sessionid;
	}

	// create() �޼���� Post����̸� ó�� �޼����, cartŬ���� ������ @RequestBody�� ���޹޾�
	// ��ٱ��ϸ� ���� �����ϰ� @ResponseBody �� ���� �մϴ�.
	@PostMapping
	public @ResponseBody Cart create(@RequestBody Cart cart) {
		// CartService �� create()�޼��� ȣ��
		return cartService.create(cart);
	}

	// requestCartList() �޼���� ��û�� url �� cartId�� �ְ�, Get����̸� ȣ��˴ϴ�.
	// �Ű����� �� cartId�� ���� ��� ������ �����ͼ� cart.jsp �� return �մϴ�.
	@GetMapping("/{cartId}")
	public String requestCartList(@PathVariable(value = "cartId") String cartId, Model model) {

		// CartService �� read()�޼��� ȣ��
		Cart cart = cartService.read(cartId);

		model.addAttribute("cart", cart);

		return "cart";
	}

	// read() �޼���� ��û�� url �� cartId�� �ְ�, Put��� �̸� ȣ��˴ϴ�.
	// Put����� ���ҽ��� ���� / ������Ʈ�ϱ� ���� ������ �����͸� ������ �� ���˴ϴ�.
	// ������ Put ��û�� ���� �� ȣ���ϸ� �׻� ������ ����� �����˴ϴ�.
	@PutMapping("/{cartId}")
	public @ResponseBody Cart read(@PathVariable(value = "cartId") String cartId) {
		return cartService.read(cartId);
	}

	// ** addCartByNewItem() �޼��� �߰� **
	// /cart/add/{bookId} ���� ��η� Put ��� ��û�� ������ �ش� ������ ��ٱ��Ͽ� �߰��� ����ϰ� ��ٱ��� ����.
	@PutMapping("/add/{bookId}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void addCartByNewItem(@PathVariable String bookId, HttpServletRequest request) {
		// ��ٱ��� ID�� ����ID ��������
		String sessionId = request.getSession(true).getId();
		// ��ٱ��Ͽ� ��ϵ� ��� ���� ������
		Cart cart = cartService.read(sessionId);

		if (cart == null)

			cart = cartService.create(new Cart(sessionId));
		// bookId �� ���� �������� ��������
		Book book = bookService.getBookById(bookId);

		if (book == null)
			throw new IllegalArgumentException(new BookIdException(bookId));
		// bookId�� ���� �������� ��ٱ��Ͽ� ����ϱ�
		cart.addCartItem(new CartItem(book));
		// ����ID�� ���� ��ٱ��� �����ϱ�
		cartService.update(sessionId, cart);
	}

	// ** removeCartByItem() �޼��� �߰� **
	@PutMapping("/remove/{bookId}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void removeCartByItem(@PathVariable String bookId, HttpServletRequest request) {
		// ��ٱ���ID�� ����ID ��������
		String sessionId = request.getSession(true).getId();
		// ��ٱ��Ͽ� ��ϵ� ��� ���� ������
		Cart cart = cartService.read(sessionId);

		if (cart == null)
			cart = cartService.create(new Cart(sessionId));
		// bookId�� ���� ���� ������
		Book book = bookService.getBookById(bookId);

		if (book == null)
			throw new IllegalArgumentException(new BookIdException(bookId));
		// bookId�� ���� ���� ������ ��ٱ��Ͽ��� �����ϱ�
		cart.removeCartItem(new CartItem(book));
		// ����ID�� ���� ��ٱ��� �����ϱ�
		cartService.update(sessionId, cart);
	}

	// ** deleteCartList() �޼��� �߰�
	@DeleteMapping("/{cartId}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deleteCartList(@PathVariable(value = "cartId") String cartId) {
		System.out.println("deleteCart ����");
		cartService.delete(cartId);
	}
}
