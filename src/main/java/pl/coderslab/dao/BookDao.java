package pl.coderslab.dao;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;
import pl.coderslab.model.Author;
import pl.coderslab.model.Book;
import pl.coderslab.model.Publisher;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class BookDao {

    @PersistenceContext
    private EntityManager entityManager;

    public void saveBook(Book book) {
        entityManager.persist(book);
    }

    public Book findById(long id) {
        Book book = entityManager.find(Book.class, id);
        Hibernate.initialize(book.getAuthors());
        return book;
    }

    public void update(Book book) {
        entityManager.merge(book);
    }

    public void delete(Book book) {
        entityManager.remove(entityManager.contains(book) ? book : entityManager.merge(book));
    }

    public List<Book> findAll() {
        return entityManager
                .createQuery("SELECT b FROM Book b")
                .getResultList();
    }

    public List<Book> findAllByRating(int rating) {
        return entityManager
                .createQuery("SELECT b FROM Book b WHERE b.rating = :rating")
                .setParameter("rating", rating)
                .getResultList();
    }

    public List<Book> getBooksWhichHaveAPublisher() {
        return entityManager.createQuery("SELECT b FROM Book b JOIN b.publisher").getResultList();
    }

    public List<Book> getBooksForPublisher(Publisher publisher) {
        return entityManager.createQuery("SELECT b FROM Book b WHERE b.publisher = :publisher")
                .setParameter("publisher", publisher)
                .getResultList();
    }

    public List<Book> getBooksForAuthor(Author author) {
        return entityManager.createQuery("SELECT DISTINCT b FROM Book b JOIN FETCH b.authors WHERE :author MEMBER OF b.authors")
                .setParameter("author", author)
                .getResultList();
    }
}
