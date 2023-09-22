/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package anderk222.stock.controller;

import org.springframework.beans.factory.annotation.Autowired;

import anderk222.stock.model.Pagination;
import anderk222.stock.model.Provider;
import anderk222.stock.service.ProviderService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author tanki
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/api/provider")
public class ProviderController {

    @Autowired
    private ProviderService service;

    @GetMapping("/search")
    public Pagination<Provider> search(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
             @RequestParam(name="size", defaultValue = "10", required = false)int size,
            @RequestParam(name="value", defaultValue = "", required = false) String value) {

        return service.search(page, size, value);
    }

    @GetMapping("/{id}")
    public Provider findById(@PathVariable long id) {

        return service.findByid(id);

    }


    @PostMapping
    public Provider save(@RequestBody Provider provider) {

        provider.setId(Long.MIN_VALUE);

        return service.save(provider);

    }

    @PutMapping("/{id}")
    public Provider update(@PathVariable long id,@RequestBody Provider provider) {

        return service.update(id, provider);
    }

    @DeleteMapping("/{id}")
    public Provider delete(@PathVariable long id) {

        return service.delete(id);

    }

}
