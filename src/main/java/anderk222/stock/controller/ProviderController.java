/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package anderk222.stock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import anderk222.stock.form.ProviderForm;
import anderk222.stock.model.Pagination;
import anderk222.stock.model.Provider;
import anderk222.stock.service.PersonService;
import anderk222.stock.service.ProviderService;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author tanki
 */
@Controller
@CrossOrigin("*")
@RequestMapping("/provider")
public class ProviderController {

    @Autowired
    private ProviderService service;

    @Autowired
    private PersonService personService;

    @GetMapping("/search")
    public String search(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size,
            @RequestParam(name = "value", defaultValue = "", required = false) String value,
            Model model) {

        Pagination<Provider> data = service.search(page, size, value);

        model.addAttribute("pagination", data);

        return "provider/providers";

    }

    @GetMapping("/{id}")
    public Provider findById(@PathVariable Long id) {

        return service.findByid(id);

    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable Long id, Model model) {

        ProviderForm provider = new ProviderForm(service.findByid(id));

        model.addAttribute("provider", provider);
        model.addAttribute("people", personService.findAll());

        return "provider/new-provider";

    }

    @GetMapping("/new")
    public String save(Model model) {

        model.addAttribute("people", personService.findAll());
        model.addAttribute("provider" , new ProviderForm());

        return "provider/new-provider";
    }

    @PostMapping
    public String save(@Valid @ModelAttribute("provider") ProviderForm provider, BindingResult result, Model model) {

        if(result.hasErrors()){
            model.addAttribute("provider", provider);
            model.addAttribute("people", personService.findAll());
            return "provider/new-provider";
        }

        provider.setId(Long.MIN_VALUE);
        service.save(new Provider(provider));
        
        return "redirect:/provider/search";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable Long id,@Valid @ModelAttribute("provider") ProviderForm provider, BindingResult result, Model model) {

        if(result.hasErrors()){

            model.addAttribute("provider", provider);
            model.addAttribute("people", personService.findAll());

            return "provider/new-provider";
        }
        
        service.update(id, new Provider(provider));

        return "redirect:/provider/search";
    }

    @DeleteMapping("/{id}")
    public Provider delete(@PathVariable Long id) {

        return service.delete(id);

    }

}
