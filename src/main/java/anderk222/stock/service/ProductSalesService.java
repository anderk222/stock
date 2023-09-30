/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package anderk222.stock.service;

import anderk222.stock.dto.ProductProjection;
import anderk222.stock.exception.ResourceNotFoundException;
import anderk222.stock.model.Product;
import anderk222.stock.model.ProductSales;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import anderk222.stock.repository.ProductSalesRepository;
import anderk222.stock.util.ProductSalesSortByCount;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 *
 * @author tanki
 */
@Service
public class ProductSalesService {

    @Autowired
    private ProductSalesRepository repository;

    @Autowired
    ProductService productService;


    @PreAuthorize("hasAuthority('PRODUCT_READ')")
    public ProductSales findByid(Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id, "id", "productSale"));

    }

    @PreAuthorize("hasAuthority('PRODUCT_READ')")
    public List<ProductSales> findBySaleyId(Long id) {

        return repository
                .findBySalesId(id);

    }

    // @PreAuthorize("hasAuthority('PRODUCT_READ')")
    public List<ProductProjection> sellest() {

        List<ProductSales> productSales = repository.findAll();

        List<ProductSales> odered = new ArrayList<>();

        for (ProductSales ps : productSales) {

            int idx = IntStream.range(0, odered.size())
                    .filter((i) -> odered.get(i).getProduct().getId().equals(ps.getProduct().getId()))
                    .findFirst().orElse(-1);

            if (idx < 0) {
                odered.add(ps);
                continue;
            }

            ProductSales _found = odered.get(idx);

            _found.setCount(_found.getCount() + ps.getCount());
        }

        odered.sort(new ProductSalesSortByCount());

        int to_idx = odered.size() > 5 ? 4 : odered.size();

        return odered.subList(0, to_idx).stream().map(ProductProjection::new).toList();
    }
    

    @PreAuthorize("hasAuthority('PRODUCT_WRITE')")
    public ProductSales save(ProductSales productSale) {

        productSale.setId(Long.MIN_VALUE);

        Product product = productService.findByid(productSale.getProduct().getId());

        product.setStock(product.getStock() - productSale.getCount());

        productSale.setProduct(product);

        return repository.save(productSale);

    }

    @PreAuthorize("hasAuthority('PRODUCT_WRITE')")
    public ProductSales update(Long id, ProductSales productSale) {
        productSale.setId(id);

        ProductSales old = this.findByid(id);
        Product product = old.getProduct();

        if (!old.getProduct().getId().equals(productSale.getProduct().getId())) {
            throw new RuntimeException("product is incorrect");
        }

        if (productSale.getCount() != old.getCount()) {

            product.setStock((product.getStock() + old.getCount()) - productSale.getCount());

            productService.save(product);

        }

        return repository.save(productSale);
    }

    @PreAuthorize("hasAuthority('PRODUCT_WRITE')")
    public ProductSales delete(Long id) {

        ProductSales productSale = findByid(id);

        repository.deleteById(id);

        return productSale;

    }

}
