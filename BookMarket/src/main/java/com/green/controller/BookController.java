package com.green.controller;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.green.domain.Book;
import com.green.exception.BookIdException;
import com.green.exception.CategoryException;
import com.green.service.BookService;

@Controller
//RequestMapping 의 단순화
@RequestMapping("/books")
public class BookController {

	// Autowired 를 정의하여 BookService 클래스의 getAllBookList() 메서드를 호출합니다.
	@Autowired
	private BookService bookService;

	/* HTTP 요청 방식이 GET인 경우, @GetMapping 을 사용할 수 있습니다. */
	@GetMapping
	public String requestBookList(Model model) {
		List<Book> list = bookService.getAllBookList();
		model.addAttribute("bookList", list);
		return "books";
	}

	// @GetMapping 에 따로 url패턴을 입력 할 수 있습니다.
	// 요청 url http://localhost:8080/myhome/books/all
	@GetMapping("/all")
	public ModelAndView requestAllBooks() {

		ModelAndView modelAndView = new ModelAndView();
		/*
		 * 메서드를 호출 한 다음 값을 담아 화면에 전달 할때 3가지 방법이 있습니다. Model - 데이터 정보를 저장하는데 사용 ModelMap
		 * - 데이터 정보를 저장하는데 사용 ModelAndView - 모델 정보 및 뷰 정보를 저장하는데 사용
		 * 
		 * 여기서 사용된 ModelAndView 는 데이터와 return 받을 뷰 정보, 즉 jsp 파일의 이름 정보를 함께 저장합니다.
		 */
		List<Book> list = bookService.getAllBookList();

		modelAndView.addObject("bookList", list); // 데이터

		modelAndView.setViewName("books"); // 뷰 정보

		return modelAndView;
	}

	// url 로 /books 이후에 요청되는 한글 GetMapping.
	@GetMapping("/{category}")
	// @PathVariable 을 정의하여 url로 전송된 도서분야 값({category})을 변수 category로 전달 받아 String
	// 타입
	// 매개변수 bookCategory 로 대입.
	public String requestBooksByCategory(@PathVariable("category") String bookCategory, Model model) {

		// service 클래스 getBookListByCategory() 메서드 호출 하여 결과값을 배열 bookByCategory에 저장.
		List<Book> booksByCategory = bookService.getBookListByCategory(bookCategory);

		// ** 예외 처리 추가하기 **

		if (booksByCategory == null || booksByCategory.isEmpty()) {
			throw new CategoryException();
		}

		model.addAttribute("bookList", booksByCategory);

		return "books";
	}

	// @MatrixVariable 로 웹 요청 url에 포함된 값을 처리
	// 요청 url :
	// http://localhost:8080/myhome/books/filter/bookFilter;publisher=길벗;category=IT전문서
	@GetMapping("/filter/{bookFilter}")
	public String requestBooksByFilter(

			@MatrixVariable(pathVar = "bookFilter") Map<String, List<String>> bookFilter, Model model) {

		// BookService 클래스 getBookListByFilter() 메서드 호출
		Set<Book> booksByFilter = bookService.getBookListByFilter(bookFilter);

		model.addAttribute("bookList", booksByFilter);

		return "books";
	}

	// @RequestParam
	@GetMapping("/book")
	public String requestBookById(@RequestParam("id") String bookId, Model model) {

		Book bookById = bookService.getBookById(bookId);

		model.addAttribute("book", bookById);

		return "book";
	}

	// 도서등록 페이지 addBook url맵핑
	@GetMapping("/add")
	public String requestAddBookForm(@ModelAttribute("NewBook") Book book) {
		return "addBook";
	}

	// 도서등록 페이지 addBook url 맵핑(post 방식)
	// **** 도서이미지 업로드 하기위해서 메서드수정 ****
	@PostMapping("/add")
	public String submitAddNewBook(@ModelAttribute("NewBook") Book book) {
		MultipartFile bookImage = book.getBookImage();

		String saveName = bookImage.getOriginalFilename();
		File saveFile = new File("C:\\spring2024\\BookMarket\\src\\main\\webapp\\resources\\images", saveName);

		if (bookImage != null && !bookImage.isEmpty()) {
			try {
				bookImage.transferTo(saveFile);
			} catch (Exception e) {
				throw new RuntimeException("도서 이미지 업로드가 실패하였습니다", e);
			}
		}

		bookService.setNewBook(book);
		return "redirect:/books";
	}

	// @ModelAttribute 를 이용해서 addBooks.jsp 페이지의 스프링 폼 태그 안의 legend 태그에 제목을 출력 합니다.
	@ModelAttribute
	public void addAttributes(Model model) {

		model.addAttribute("addTitle", "신규 도서 등록");
	}

	// @IntitBinder 를 이용해서 폼 페이지 에서 입력되는 파라미터와 도메인 클래스의 필드를 모두 연결 할 수있습니다.
	// **** 도서이미지 업로드 하기위해서 메서드수정 ****
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setAllowedFields("bookId", "name", "unitPrice", "author", "description", "publisher", "category",
				"unitsInStock", "totalPages", "releaseDate", "condition", "bookImage");
	}

	// @ExceptionHandler : 웹 요청에 따라 처리하는 컨트롤러에서 예외가 발생하면 처리하기 위해 작성합니다.
	// @ExceptionHandler 에서 value속성은 예외 처리를 담당하는 클래스 이름을 설정합니다
	@ExceptionHandler(value = { BookIdException.class })
	public ModelAndView handleError(HttpServletRequest req, BookIdException exception) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("invalidBookId", exception.getBookId());
		mav.addObject("exception", exception);
		mav.addObject("url", req.getRequestURL() + "?" + req.getQueryString());
		// 예외 상황 발생시 errorBook.jsp 로 return
		mav.setViewName("errorBook");

		return mav;
	}
}
