/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package anderk222.stock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import anderk222.stock.form.ShoppingForm;
import anderk222.stock.model.Pagination;
import anderk222.stock.model.Product;
import anderk222.stock.service.CategoryService;
import anderk222.stock.service.ProductService;
import anderk222.stock.service.ProviderService;
import anderk222.stock.service.SalesService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
@RequestMapping("/product")
@CrossOrigin("*")
public class ProductController {

    @Autowired
    private ProductService service;

    @Autowired
    private SalesService salesService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProviderService providerService;

    @GetMapping("/search")
    public String search(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size,
            @RequestParam(name = "value", defaultValue = "", required = false) String value,
            Model model) {

        Pagination<Product> data = service.search(page, size, value);

        model.addAttribute("pagination", data);

        return "product/products";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable long id, Model model) {

        Product product = service.findByid(id);

        ShoppingForm shop = new ShoppingForm(1, 1l, 1l);

        model.addAttribute("product", product);
        model.addAttribute("shop", shop);

        return "product/product";

    }

    @PostMapping("/buy")
    public RedirectView buy(@ModelAttribute("shop") ShoppingForm shop) {

        salesService.buy(shop);

        return new RedirectView("/product/search");

    }

    @GetMapping("/{provider}/provider")
    public String findByProviderId(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "0", required = false) int size,
            @PathVariable long provider, Model model) {

        Pagination<Product> data = service.findByProviderId(provider, page, size);

        model.addAttribute("pagination", data);

        return "product/products";

    }

    @GetMapping("/new")
    public String save(Model model) {

        model.addAttribute("product", new Product());

        model.addAttribute("categories", categoryService.findAll());

        model.addAttribute("providers", providerService.findAll());

        return "product/new-product";

    }

    @GetMapping("/update/{id}")
    public String save(@PathVariable long id, Model model) {

        model.addAttribute("product", service.findByid(id));

        model.addAttribute("categories", categoryService.findAll());

        model.addAttribute("providers", providerService.findAll());

        return "product/new-product";

    }

    @GetMapping("/category/{category}")
    public String findByCategoryId(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "0", required = false) int size,
            @PathVariable long category, Model model) {

        Pagination<Product> data = service.findByCategoryId(category, page, size);

        model.addAttribute("products", data);

        return "product/products";

    }

    @PostMapping
    public RedirectView save(@RequestBody Product product) {

        return new RedirectView("product/search");

    }

    @PostMapping("/{id}")
    public RedirectView update(@PathVariable long id, @RequestBody Product product) {

        return new RedirectView("search");
    }

    @GetMapping("/delete/{id}")
    public RedirectView delete(long id) {

        service.delete(id);

        return new RedirectView("/product/search");

    }

}
