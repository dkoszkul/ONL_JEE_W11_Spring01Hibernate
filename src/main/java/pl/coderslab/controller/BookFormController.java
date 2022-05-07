package pl.coderslab.controller;

import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.dao.AuthorDao;
import pl.coderslab.dao.BookDao;
import pl.coderslab.dao.PublisherDao;
import pl.coderslab.model.Author;
import pl.coderslab.model.Book;
import pl.coderslab.model.Publisher;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("bookForm")
@RequiredArgsConstructor
public class BookFormController {

    private final BookDao bookDao;
    private final AuthorDao authorDao;
    private final PublisherDao publisherDao;

    @GetMapping("add")
    public String addNewBook(Model model) {
        model.addAttribute("book", new Book());
        return "bookForm-add";
    }

    @PostMapping("add")
    public String saveNewBook(@ModelAttribute("book") @Valid Book book, BindingResult result) {
        if (result.hasErrors()) {
            return "bookForm-add";
        }

        if (book.getId() != null) {
            bookDao.update(book);
        } else {
            bookDao.saveBook(book);
        }
        return "redirect:/bookForm/all";
    }

    @GetMapping("all")
    public String showAllBooks(Model model) {
        model.addAttribute("books", bookDao.findAll());
        return "bookForm-all";
    }

    // localhost:8080/bookForm/edit/2
    @GetMapping("edit/{id}")
    public String editBook(@PathVariable("id") long id,
                           Model model) {
        Book book = bookDao.findById(id);
        model.addAttribute("book", book);
        return "bookForm-add";
    }

    @ModelAttribute("publishers")
    public List<Publisher> getAllPublishers() {
        return publisherDao.findAll();
    }

    @ModelAttribute("authors")
    public List<Author> getAllAuthors() {
        return authorDao.findAll();
    }
}
