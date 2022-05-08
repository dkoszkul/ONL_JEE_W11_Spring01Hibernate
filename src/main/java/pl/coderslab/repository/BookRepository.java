package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.coderslab.model.Book;
import pl.coderslab.model.Category;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    // metodę wyszukującą książki dla zadanego tytułu
    Optional<Book> findByTitle(String title);

    // metodę wyszukującą książki dla zadanej kategorii
    List<Book> findByCategory(Category category);

    // metodę wyszukującą książki dla zadanego id kategorii
    List<Book> findByCategoryId(Long categoryId);

    List<Book> findByAuthors_FirstName(String authorFirstName);

    // day 4
    //metodę wyszukującą książki dla zadanego tytułu.
    @Query("SELECT b FROM Book b WHERE b.title = :title")
    Optional<Book> customFindBookByTitle(@Param("title") String title);

    //metodę wyszukującą książki dla zadanej kategorii
    @Query("SELECT b FROM Book b WHERE b.category = :category")
    List<Book> customFindByCategory(@Param("category") Category category);
}
