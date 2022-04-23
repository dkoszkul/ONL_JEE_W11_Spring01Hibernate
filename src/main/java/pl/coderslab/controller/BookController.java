package pl.coderslab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.coderslab.dao.BookDao;
import pl.coderslab.model.Book;

@Controller
public class BookController {
    private BookDao bookDao;

    public BookController(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @GetMapping("add")
    public String addBook() {
        Book book = new Book();
        book.setTitle("Title 1");
        book.setDescription("Description 1");
        book.setRating(3);
        bookDao.saveBook(book);

        return "book id is " + book.getId();
    }

    @GetMapping("get/{id}")
    public String getBook(@PathVariable long id) {
        Book book = bookDao.findById(id);
        return book.toString();
    }

    @GetMapping("update/{id}/{title}")
    public String updateBook(@PathVariable long id, @PathVariable String title) {
        Book book = bookDao.findById(id);
        book.setTitle(title);
        bookDao.update(book);
        return book.toString();
    }

    @GetMapping("delete/{id}")
    public String deleteBook(@PathVariable long id) {
        Book book = bookDao.findById(id);
        bookDao.delete(book);
        return "deleted";
    }
}
