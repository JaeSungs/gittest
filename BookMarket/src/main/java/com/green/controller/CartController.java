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

	// @Autowired 를 이용해서 CartService 클래스 객체를 등록 합니다.
	@Autowired
	private CartService cartService;

	// ** BookService 클래스 @Autowired 추가 **
	@Autowired
	private BookService bookService;

	// requestCartId() 메서드는 /cart 로 url 요청이 들어오면 세션에 저장된 id값을
	// 가져와서 /cart/ 로 sessionid 를 추가하여 리다이렉션 합니다.
	@GetMapping
	public String requestCartId(HttpServletRequest request) {
		String sessionid = request.getSession(true).getId();
		return "redirect:/cart/" + sessionid;
	}

	// create() 메서드는 Post방식이면 처리 메서드로, cart클래스 정보를 @RequestBody로 전달받아
	// 장바구니를 새로 생성하고 @ResponseBody 로 응답 합니다.
	@PostMapping
	public @ResponseBody Cart create(@RequestBody Cart cart) {
		// CartService 의 create()메서드 호출
		return cartService.create(cart);
	}

	// requestCartList() 메서드는 요청된 url 에 cartId가 있고, Get방식이면 호출됩니다.
	// 매개변수 인 cartId에 대한 모든 정보를 가져와서 cart.jsp 로 return 합니다.
	@GetMapping("/{cartId}")
	public String requestCartList(@PathVariable(value = "cartId") String cartId, Model model) {

		// CartService 의 read()메서드 호출
		Cart cart = cartService.read(cartId);

		model.addAttribute("cart", cart);

		return "cart";
	}

	// read() 메서드는 요청된 url 에 cartId가 있고, Put방식 이면 호출됩니다.
	// Put방식은 리소스를 생성 / 업데이트하기 위해 서버로 데이터를 보내는 데 사용됩니다.
	// 동일한 Put 요청을 여러 번 호출하면 항상 동일한 결과가 생성됩니다.
	@PutMapping("/{cartId}")
	public @ResponseBody Cart read(@PathVariable(value = "cartId") String cartId) {
		return cartService.read(cartId);
	}

	// ** addCartByNewItem() 메서드 추가 **
	// /cart/add/{bookId} 매핑 경로로 Put 방식 요청이 들어오면 해당 도서를 장바구니에 추가로 등록하고 장바구니 갱신.
	@PutMapping("/add/{bookId}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void addCartByNewItem(@PathVariable String bookId, HttpServletRequest request) {
		// 장바구니 ID인 세션ID 가져오기
		String sessionId = request.getSession(true).getId();
		// 장바구니에 등록된 모든 정보 얻어오기
		Cart cart = cartService.read(sessionId);

		if (cart == null)

			cart = cartService.create(new Cart(sessionId));
		// bookId 에 대한 도서정보 가져오기
		Book book = bookService.getBookById(bookId);

		if (book == null)
			throw new IllegalArgumentException(new BookIdException(bookId));
		// bookId에 대한 도서정보 장바구니에 등록하기
		cart.addCartItem(new CartItem(book));
		// 세션ID에 대한 장바구니 갱신하기
		cartService.update(sessionId, cart);
	}

	// ** removeCartByItem() 메서드 추가 **
	@PutMapping("/remove/{bookId}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void removeCartByItem(@PathVariable String bookId, HttpServletRequest request) {
		// 장바구니ID인 세션ID 가져오기
		String sessionId = request.getSession(true).getId();
		// 장바구니에 등록된 모든 정보 얻어오기
		Cart cart = cartService.read(sessionId);

		if (cart == null)
			cart = cartService.create(new Cart(sessionId));
		// bookId에 대한 정보 얻어오기
		Book book = bookService.getBookById(bookId);

		if (book == null)
			throw new IllegalArgumentException(new BookIdException(bookId));
		// bookId에 대한 도서 정보를 장바구니에서 삭제하기
		cart.removeCartItem(new CartItem(book));
		// 세션ID에 대한 장바구니 갱신하기
		cartService.update(sessionId, cart);
	}

	// ** deleteCartList() 메서드 추가
	@DeleteMapping("/{cartId}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deleteCartList(@PathVariable(value = "cartId") String cartId) {
		System.out.println("deleteCart 진입");
		cartService.delete(cartId);
	}
}
