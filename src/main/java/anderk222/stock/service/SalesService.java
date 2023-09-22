/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package anderk222.stock.service;

import anderk222.stock.exception.ResourceNotFoundException;
import anderk222.stock.model.Pagination;
import anderk222.stock.model.Product;
import anderk222.stock.model.ProductSales;
import anderk222.stock.model.Sales;
import anderk222.stock.model.SalesProjection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    public Sales findByid(long id) {
        
        Sales sales =repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id, "id", "sale"));

        BigDecimal total = BigDecimal.ZERO;
        
        for(ProductSales item : sales.getProductSalesList()){
            
            total = total.add(item.getProduct()
                    .getPrice().multiply(BigDecimal.valueOf(item.getCount())));
            
        }
        
        sales.setTotal(total);
        
        return sales;
    }

    public Pagination<SalesProjection> findByPersonId(long id, int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        Page<SalesProjection> data = repository
                .findByPersonId(id, pageable);

        Pagination<SalesProjection> res = new Pagination<>(page, size, data.getContent());

        res.setTotalPages(data.getTotalPages());
        res.setTotaltems(data.getTotalElements());

        return res;

    }

    public Sales save(Sales sale) {

        sale.setId(Long.MIN_VALUE);
        
        this.updateStock(sale);

        return repository.save(sale);

    }
    
    public List<Sales> saveAll(List<Sales> sales){
        
        return repository.saveAll(sales);
        
    }

    public Sales update(long id, Sales sale) {
        
        List<ProductSales> old_product_sale = findByid(id).getProductSalesList();
       
        for(ProductSales productSales : sale.getProductSalesList()){
            
            Optional<ProductSales>  product_old_sale = old_product_sale.stream()
                    .filter((sp)->sp.getId()==productSales.getId()).findFirst();
            
            Product product;
            int updated_stock;

            if(product_old_sale.isPresent()){
                
                product = product_old_sale.get().getProduct();
                
                if((product_old_sale.get().getCount() != productSales.getCount())){
                    
                    updated_stock = 
                            (product.getStock()+ product_old_sale.get().getCount()) - productSales.getCount();
                
                    product.setStock(updated_stock);
                    
                    productService.save(product);
                }
            }else{
                
                product = productService.findByid(productSales.getProduct().getId());   
                updated_stock = product.getStock() - productSales.getCount();
                
                product.setStock(updated_stock);

                productService.save(product);
            }
            
        }

        return repository.save(sale);
    }

    public Sales delete(long id) {

        Sales sale = this.findByid(id);

        repository.deleteById(id);

        return sale;

    }
    
    private void updateStock(Sales sale){
        
        
         for(ProductSales product_sale: sale.getProductSalesList()){

            int count = product_sale.getCount();
            int stock = product_sale.getProduct().getStock();
            
            Product _product= productService
                    .findByid(product_sale.getProduct().getId());
            
            _product.setStock(count - stock);
            
            productService.save(_product);
            
        }
    }
}
