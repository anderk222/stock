package anderk222.stock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import anderk222.stock.dto.SalesProjection;
import anderk222.stock.model.Pagination;
import anderk222.stock.model.Sales;
import anderk222.stock.service.SalesService;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author tanki
 */

@Controller
@RequestMapping("/shopping")
public class ShoppingController {

    @Autowired
    private SalesService service;

    @GetMapping("/{id}")
    public Sales findById(Long id) {

        return service.findByid(id);
    }

    @GetMapping("/{person}/person")
    public String findByPersonId(
            @PathVariable Long person,
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size,
            Model model) {

        Pagination<SalesProjection> data = service.findByPersonId(person, page, size);

        model.addAttribute("shoping", data);

        return "shopping/purchases";

    }

    @GetMapping("/shop-cart")
    public String shopCar(Model model){

        model.addAttribute("sale",new Sales());

        return "shopping/shop-cart";
    }

    @PostMapping
    public RedirectView save(@RequestBody Sales sale) {

        return new RedirectView("shopping/purchases");
    }

    @PostMapping("/all")
    public List<Sales> saveAll(@RequestBody List<Sales> sale) {

        return service.saveAll(sale);

    }

    @PutMapping("/{id}")
    public Sales update(@PathVariable Long id, @RequestBody Sales sale) {

        return service.save(sale);
    }

    @DeleteMapping("/{id}")
    public Sales delete(@PathVariable Long id) {
        return service.delete(id);

    }

    @GetMapping("/purchases/{person}")
    public String purchases(
        Model model, 
        @RequestParam( name = "page", defaultValue="0", required=false ) int page,
        @RequestParam(name = "size", defaultValue = "10", required = false) int size,
        @PathVariable Long person){

        Pagination<SalesProjection> data = service.findByPersonId(person, page, size);

        model.addAttribute("pagination", data);

        return "shopping/purchases";

    }

    @GetMapping("/purchase/{id}")
    public String purchase(Model model,@PathVariable Long id){

        Sales data = service.findByid(id);
        
        model.addAttribute("purchase", data);

        return "shopping/purchase";
        
    }

}
