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
//RequestMapping �� �ܼ�ȭ
@RequestMapping("/books")
public class BookController {

	// Autowired �� �����Ͽ� BookService Ŭ������ getAllBookList() �޼��带 ȣ���մϴ�.
	@Autowired
	private BookService bookService;

	/* HTTP ��û ����� GET�� ���, @GetMapping �� ����� �� �ֽ��ϴ�. */
	@GetMapping
	public String requestBookList(Model model) {
		List<Book> list = bookService.getAllBookList();
		model.addAttribute("bookList", list);
		return "books";
	}

	// @GetMapping �� ���� url������ �Է� �� �� �ֽ��ϴ�.
	// ��û url http://localhost:8080/myhome/books/all
	@GetMapping("/all")
	public ModelAndView requestAllBooks() {

		ModelAndView modelAndView = new ModelAndView();
		/*
		 * �޼��带 ȣ�� �� ���� ���� ��� ȭ�鿡 ���� �Ҷ� 3���� ����� �ֽ��ϴ�. Model - ������ ������ �����ϴµ� ��� ModelMap
		 * - ������ ������ �����ϴµ� ��� ModelAndView - �� ���� �� �� ������ �����ϴµ� ���
		 * 
		 * ���⼭ ���� ModelAndView �� �����Ϳ� return ���� �� ����, �� jsp ������ �̸� ������ �Բ� �����մϴ�.
		 */
		List<Book> list = bookService.getAllBookList();

		modelAndView.addObject("bookList", list); // ������

		modelAndView.setViewName("books"); // �� ����

		return modelAndView;
	}

	// url �� /books ���Ŀ� ��û�Ǵ� �ѱ� GetMapping.
	@GetMapping("/{category}")
	// @PathVariable �� �����Ͽ� url�� ���۵� �����о� ��({category})�� ���� category�� ���� �޾� String
	// Ÿ��
	// �Ű����� bookCategory �� ����.
	public String requestBooksByCategory(@PathVariable("category") String bookCategory, Model model) {

		// service Ŭ���� getBookListByCategory() �޼��� ȣ�� �Ͽ� ������� �迭 bookByCategory�� ����.
		List<Book> booksByCategory = bookService.getBookListByCategory(bookCategory);

		// ** ���� ó�� �߰��ϱ� **

		if (booksByCategory == null || booksByCategory.isEmpty()) {
			throw new CategoryException();
		}

		model.addAttribute("bookList", booksByCategory);

		return "books";
	}

	// @MatrixVariable �� �� ��û url�� ���Ե� ���� ó��
	// ��û url :
	// http://localhost:8080/myhome/books/filter/bookFilter;publisher=���;category=IT������
	@GetMapping("/filter/{bookFilter}")
	public String requestBooksByFilter(

			@MatrixVariable(pathVar = "bookFilter") Map<String, List<String>> bookFilter, Model model) {

		// BookService Ŭ���� getBookListByFilter() �޼��� ȣ��
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

	// ������� ������ addBook url����
	@GetMapping("/add")
	public String requestAddBookForm(@ModelAttribute("NewBook") Book book) {
		return "addBook";
	}

	// ������� ������ addBook url ����(post ���)
	// **** �����̹��� ���ε� �ϱ����ؼ� �޼������ ****
	@PostMapping("/add")
	public String submitAddNewBook(@ModelAttribute("NewBook") Book book) {
		MultipartFile bookImage = book.getBookImage();

		String saveName = bookImage.getOriginalFilename();
		File saveFile = new File("C:\\spring2024\\BookMarket\\src\\main\\webapp\\resources\\images", saveName);

		if (bookImage != null && !bookImage.isEmpty()) {
			try {
				bookImage.transferTo(saveFile);
			} catch (Exception e) {
				throw new RuntimeException("���� �̹��� ���ε尡 �����Ͽ����ϴ�", e);
			}
		}

		bookService.setNewBook(book);
		return "redirect:/books";
	}

	// @ModelAttribute �� �̿��ؼ� addBooks.jsp �������� ������ �� �±� ���� legend �±׿� ������ ��� �մϴ�.
	@ModelAttribute
	public void addAttributes(Model model) {

		model.addAttribute("addTitle", "�ű� ���� ���");
	}

	// @IntitBinder �� �̿��ؼ� �� ������ ���� �ԷµǴ� �Ķ���Ϳ� ������ Ŭ������ �ʵ带 ��� ���� �� ���ֽ��ϴ�.
	// **** �����̹��� ���ε� �ϱ����ؼ� �޼������ ****
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setAllowedFields("bookId", "name", "unitPrice", "author", "description", "publisher", "category",
				"unitsInStock", "totalPages", "releaseDate", "condition", "bookImage");
	}

	// @ExceptionHandler : �� ��û�� ���� ó���ϴ� ��Ʈ�ѷ����� ���ܰ� �߻��ϸ� ó���ϱ� ���� �ۼ��մϴ�.
	// @ExceptionHandler ���� value�Ӽ��� ���� ó���� ����ϴ� Ŭ���� �̸��� �����մϴ�
	@ExceptionHandler(value = { BookIdException.class })
	public ModelAndView handleError(HttpServletRequest req, BookIdException exception) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("invalidBookId", exception.getBookId());
		mav.addObject("exception", exception);
		mav.addObject("url", req.getRequestURL() + "?" + req.getQueryString());
		// ���� ��Ȳ �߻��� errorBook.jsp �� return
		mav.setViewName("errorBook");

		return mav;
	}
}
