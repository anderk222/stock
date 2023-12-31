/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package anderk222.stock.service;

import anderk222.stock.dto.SalesProjection;
import anderk222.stock.exception.ResourceNotFoundException;
import anderk222.stock.form.ProductCart;
import anderk222.stock.form.ShoppingForm;
import anderk222.stock.model.Pagination;
import anderk222.stock.model.Person;
import anderk222.stock.model.Product;
import anderk222.stock.model.ProductSales;
import anderk222.stock.model.Sales;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import anderk222.stock.repository.SalesRepository;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author tanki
 */
@Service
public class SalesService {

    @Autowired
    private SalesRepository repository;

    @Autowired
    private ProductService productService;

    @PreAuthorize("hasAuthority('SALES_READ')")
    public Sales findByid(Long id) {

        Sales sales = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id, "id", "sale"));

        BigDecimal total = BigDecimal.ZERO;

        for (ProductSales item : sales.getProductSalesList()) {

            total = total.add(item.getProduct()
                    .getPrice().multiply(BigDecimal.valueOf(item.getCount())));

        }

        sales.setTotal(total);

        return sales;
    }

    @PreAuthorize("hasAuthority('SALES_READ')")

    public Pagination<SalesProjection> findByPersonId(Long id, int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        Page<SalesProjection> data = repository
                .findByPersonId(id, pageable);

        Pagination<SalesProjection> res = new Pagination<>(page, size, data.getContent());

        res.setNext(pageable.next().getPageNumber());
        res.setPrevious(pageable.hasPrevious() ? page - 1 : 1);

        res.setTotalPages(data.getTotalPages());
        res.setTotaltems(data.getTotalElements());

        return res;

    }

    @PreAuthorize("hasAuthority('SALES_READ')")
    public Sales buy(ShoppingForm shoppingForm) {

        Sales sale = repository.save(new Sales());

        sale.addProduct(new Product(shoppingForm.getProduct()), shoppingForm.getCount());

        sale.setPerson(new Person(shoppingForm.getPerson()));

        return repository.save(sale);
    }

    @PreAuthorize("hasAuthority('SALES_READ')")
    public Sales buy(List<ProductCart> products, Long user) {

        // String uuid = UUID.randomUUID().toString();

        Sales sale = repository.save(new Sales(new Person(user)));

        for (ProductCart pCart : products)
            sale.addProduct(Product.fromProductCart(pCart), pCart.getCount());

        return repository.save(sale);

    }

    @PreAuthorize("hasAuthority('SALES_WRITE')")
    public Sales save(Sales sale) {

        sale.setId(Long.MIN_VALUE);

        this.updateStock(sale);

        return repository.save(sale);

    }

    @PreAuthorize("hasAuthority('SALES_WRITE')")
    public List<Sales> saveAll(List<Sales> sales) {

        return repository.saveAll(sales);

    }

    @PreAuthorize("hasAuthority('SALES_WRITE')")
    public Sales update(Long id, Sales sale) {

        List<ProductSales> old_product_sale = findByid(id).getProductSalesList();

        for (ProductSales productSales : sale.getProductSalesList()) {

            Optional<ProductSales> product_old_sale = old_product_sale.stream()
                    .filter((sp) -> sp.getId() == productSales.getId()).findFirst();

            Product product;
            int updated_stock;

            if (product_old_sale.isPresent()) {

                product = product_old_sale.get().getProduct();

                if ((product_old_sale.get().getCount() != productSales.getCount())) {

                    updated_stock = (product.getStock() + product_old_sale.get().getCount()) - productSales.getCount();

                    product.setStock(updated_stock);

                    productService.save(product);
                }
            } else {

                product = productService.findByid(productSales.getProduct().getId());
                updated_stock = product.getStock() - productSales.getCount();

                product.setStock(updated_stock);

                productService.save(product);
            }

        }

        return repository.save(sale);
    }

    public Sales delete(Long id) {

        Sales sale = this.findByid(id);

        repository.deleteById(id);

        return sale;

    }

    private void updateStock(Sales sale) {

        for (ProductSales product_sale : sale.getProductSalesList()) {

            int count = product_sale.getCount();
            int stock = product_sale.getProduct().getStock();

            Product _product = productService
                    .findByid(product_sale.getProduct().getId());

            _product.setStock(count - stock);

            productService.save(_product);

        }
    }
}
