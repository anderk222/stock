/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package anderk222.stock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import anderk222.stock.model.Pagination;
import anderk222.stock.model.Sales;
import anderk222.stock.model.SalesProjection;
import anderk222.stock.service.SalesService;
import java.util.List;
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
@RequestMapping("/sales")
public class SalesController {

    @Autowired
    private SalesService service;

    @GetMapping("/{id}")
    public Sales findById(long id) {

        return service.findByid(id);
    }

    @GetMapping("/{person}/person")
    public Pagination<SalesProjection> findByPersonId(
            @PathVariable long person,
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size
    ) {

        return service.findByPersonId(person, page, size);

    }

    @PostMapping
    public Sales save(@RequestBody Sales sale) {

        return service.save(sale);

    }

    @PostMapping("/all")
    public List<Sales> saveAll(@RequestBody List<Sales> sale) {

        return service.saveAll(sale);

    }

    @PutMapping("/{id}")
    public Sales update(@PathVariable long id, @RequestBody Sales sale) {

        return service.save(sale);
    }

    @DeleteMapping("/{id}")
    public Sales delete(@PathVariable long id) {
        return service.delete(id);

    }
}
