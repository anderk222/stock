package anderk222.stock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import anderk222.stock.service.ProductSalesService;
import anderk222.stock.service.ProductService;

@Controller
public class MainController {

    @Autowired
    ProductSalesService productSalesService;

    @Autowired 
    private ProductService productService;

    @GetMapping()
    public String home(Model model) {

        model.addAttribute("sellest",  productSalesService.sellest());

        model.addAttribute("others", productService.search(0, 5, ""));
        return "index";
    }

    // Login form
    @RequestMapping("/login.html")
    public String login() {
        return "login.html";
    }

    // Login form with error
    @RequestMapping("/login-error.html")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login.html";
    }

    @RequestMapping("/register.html")
    public String register() {
        return "register.html";
    }
}
