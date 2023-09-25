package anderk222.stock.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import anderk222.stock.model.ProductProjection;
import anderk222.stock.service.ProductSalesService;

@Controller
public class HelloController {

    @Autowired ProductSalesService productSalesService;

    @GetMapping()
    public String home(Model model){

        List<ProductProjection> most_sellest = productSalesService.sellest();

        model.addAttribute("sellest", most_sellest);
        return "index"; 
    }
}  
