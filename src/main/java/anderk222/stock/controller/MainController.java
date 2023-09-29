package anderk222.stock.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import anderk222.stock.dto.ProductProjection;
import anderk222.stock.service.ProductSalesService;

@Controller
public class MainController {

    @Autowired
    ProductSalesService productSalesService;

    @GetMapping()
    public String home(Model model) {

        List<ProductProjection> most_sellest = productSalesService.sellest();

        model.addAttribute("sellest", most_sellest);
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
