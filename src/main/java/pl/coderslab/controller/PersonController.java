package pl.coderslab.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.dao.PersonDao;
import pl.coderslab.model.Person;

@Slf4j
@Controller
@RequiredArgsConstructor
public class PersonController {

    private final PersonDao personDao;

    @GetMapping("person")
    public String personForm(Model model) {
        model.addAttribute("person", new Person());
        return "personForm";
    }

    @PostMapping("person")
    @ResponseBody
    public String handlePersonForm(@ModelAttribute("person") Person person) {
        log.info("New person {}", person);
        personDao.save(person);
        return "OK";
    }
}
