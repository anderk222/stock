/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package anderk222.stock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import anderk222.stock.model.ProductSales;
import anderk222.stock.service.ProductSalesService;
import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author tanki
 */
@Controller
@CrossOrigin("*")
@RequestMapping("/api/product-sales")
public class ProductSalesController {

    @Autowired
    private ProductSalesService service;

    @GetMapping("/{id}")
    public ProductSales findById(@PathVariable long id) {

        return service.findByid(id);

    }

    @GetMapping("/{sale}/sale")
    public List<ProductSales> findBySaleId(@PathVariable long sale) {

        return service
                .findBySaleyId(sale);
    }

    @PostMapping
    public ProductSales save(@RequestBody ProductSales productSale) {

        return service.save(productSale);

    }

    @PutMapping("/{id}")
    public ProductSales update(@PathVariable long id, @RequestBody ProductSales productSale) {

        return service.update(id, productSale);
    }

    @DeleteMapping("/{id}")
    public ProductSales delete(long id) {

        return service.delete(id);

    }
}
