/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package anderk222.stock.controller;

import anderk222.stock.model.Category;
import anderk222.stock.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author tanki
 */

@Controller
@CrossOrigin("*")
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private CategoryService service;

    @GetMapping("/search")
    public String search(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size,
            @RequestParam(name = "value", defaultValue = "", required = false) String value,
            Model model) {

        model.addAttribute("pagination", service
                .search(page, size, value));

        return "category/categories";

    }

    @GetMapping("/{id}")
    public Category findByid(@PathVariable long id) {

        return service.findByid(id);

    }

    @PostMapping
    public Category save(@RequestBody Category category) {

        return service.save(category);

    }

    @PutMapping("/{id}")
    public Category update(@PathVariable long id, Category category) {

        return service.update(id, category);
    }

    @DeleteMapping("/{id}")
    public Category delete(@PathVariable long id) {

        return service.delete(id);

    }
}
