package anderk222.stock.controller;

import anderk222.stock.model.Pagination;
import anderk222.stock.model.Person;
import anderk222.stock.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author tanki
 */

@Controller
@CrossOrigin("*")
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService service;

    @GetMapping("/search")
    public Pagination<Person> search(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size,
            @RequestParam(name = "value", defaultValue = "", required = false) String value) {

        return service.search(page, size, value);
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable long id, Model model) {

       model.addAttribute("person", service.findByid(id));

        return "person/new-person";

    }

    @PostMapping()
    public RedirectView save(@RequestBody Person person) {

        return new RedirectView("person/search");

    }

    @PostMapping("/{id}")
    public RedirectView update(@PathVariable long id, @RequestBody Person person) {

        return new RedirectView("person/search");

    }

    @GetMapping("/delete/{id}")
    public RedirectView delete(@PathVariable long id) {

        service.delete(id);
        return new RedirectView("person/search");

    }
}
