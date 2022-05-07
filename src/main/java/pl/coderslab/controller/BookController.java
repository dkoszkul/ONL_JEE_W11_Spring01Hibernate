package pl.coderslab.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.dao.BookDao;
import pl.coderslab.dao.PublisherDao;
import pl.coderslab.model.Book;
import pl.coderslab.model.Publisher;
import pl.coderslab.repository.BookRepository;

@Slf4j
@Controller
@RequiredArgsConstructor
public class BookController {
    private final BookDao bookDao;
    private final PublisherDao publisherDao;
    private final BookRepository bookRepository;

    @GetMapping("add")
    @ResponseBody
    public String addBook() {
        log.info("Add new book");
        Publisher publisher = new Publisher();
        publisher.setName("Dominik");
        publisherDao.save(publisher);
        log.info("Added publisher to db, new id: {}", publisher.getId());

        Book book = new Book();
        book.setPublisher(publisher);
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

    @GetMapping(value = "update/{id}/{title}", produces = "text/plain")
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
