package pl.coderslab.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.coderslab.model.Book;
import pl.coderslab.model.Publisher;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ValidationController {

    private final Validator validator;

    @GetMapping("validate")
    public void validateObject() {
        Book book = new Book(); // tworzymy nowy obiekt typu Book (pusty, wiec nie przejdzie walidacji)

        Set<ConstraintViolation<Book>> result = validator.validate(book); // odpalamy walidację
        if (!result.isEmpty()) {  // sprawdzamy, czy są jakieś błędy
            for (ConstraintViolation<Book> violation : result) {  // wyświetlamy te błędy
                log.warn("{} -> {}", violation.getPropertyPath(), violation.getMessage());
            }
        }
    }
}
