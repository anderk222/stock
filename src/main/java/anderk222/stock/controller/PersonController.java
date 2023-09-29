package anderk222.stock.controller;

import anderk222.stock.form.PersonForm;
import anderk222.stock.model.Pagination;
import anderk222.stock.model.Person;
import anderk222.stock.service.PersonService;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
    public String search(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size,
            @RequestParam(name = "value", defaultValue = "", required = false) String value,
            Model model) {

        Pagination<Person> data = service.search(page, size, value);

        model.addAttribute("pagination", data);

        return "person/people";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable Long id, Model model) {

        model.addAttribute("person", service.findByid(id));

        return "person/new-person";

    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable Long id, Model model) {

        PersonForm person = new PersonForm(service.findByid(id));

        model.addAttribute("person", person);

        return "person/new-person";

    }

    @GetMapping("/new")
    public String update(Model model) {

        model.addAttribute("person", new PersonForm());

        return "person/new-person";

    }

    @PostMapping()
    public String save(@Valid @ModelAttribute("person") PersonForm person, BindingResult result, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("person", person);

            return "person/new-person";
        }

        service.save(Person.fromPersonForm(person));

        return "redirect:/person/search";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable Long id,@Valid @ModelAttribute("person") PersonForm person, BindingResult result, Model model) {

         if(result.hasErrors()){

            model.addAttribute("person",person);

            return "person/new-person";
         }
        service.update(id, Person.fromPersonForm(person));

        return "redirect:/person/search";

    }

    @GetMapping("/delete/{id}")
    public RedirectView delete(@PathVariable Long id) {

        service.delete(id);
        return new RedirectView("person/search");

    }
}
