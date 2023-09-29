/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package anderk222.stock.controller;

import org.springframework.beans.factory.annotation.Autowired;

import anderk222.stock.model.ProductDetail;
import anderk222.stock.service.ProductDetailService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author tanki
 */

@RestController
@RequestMapping("/api/product_detail")
public class ProductDetailController {

    @Autowired
    private ProductDetailService service;

    @GetMapping("/{id}")
    public ProductDetail findById(@PathVariable Long id) {

        return service.findByid(id);

    }

    @PostMapping
    public ProductDetail save(@RequestBody ProductDetail productDetail) {

        return service.save(productDetail);

    }

    @PutMapping("/{id}")
    public ProductDetail update(@PathVariable Long id,@RequestBody ProductDetail productDetail) {

        return service.save(productDetail);
    }

    @DeleteMapping("/{id}")
    public ProductDetail delete(@PathVariable Long id) {


        return service.delete(id);

    }

}
