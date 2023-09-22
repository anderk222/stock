/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package anderk222.stock.service;

import anderk222.stock.exception.ResourceNotFoundException;
import anderk222.stock.model.Pagination;
import anderk222.stock.model.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import anderk222.stock.repository.ProductRepository;
import jakarta.transaction.Transactional;

/**
 *
 * @author tanki
 */
@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public Pagination<Product> search(int page, int size, String value) {

        Pageable pageable = PageRequest.of(page, size);

        Page<Product> data = repository
                .findByNameContainingIgnoreCase(value, pageable);

        Pagination<Product> res = new Pagination<>(page, size, data.getContent());

        res.setTotalPages(data.getTotalPages());
        res.setTotaltems(data.getTotalElements());

        return res;
    }

    public Product findByid(long id) {

        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id, "id", "product"));

    }

    public Pagination<Product> findByProviderId(long id, int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        Page<Product> data = repository
                .findByProductDetailProviderId(id,pageable);

        Pagination<Product> res = new Pagination<>(page, size, data.getContent());

        res.setTotalPages(data.getTotalPages());
        res.setTotaltems(data.getTotalElements());

        return res;

    }
    
        public Pagination<Product> findByCategoryId(long id, int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        Page<Product> data = repository
                .findByProductDetailCategoryId(id,pageable);

        Pagination<Product> res = new Pagination<>(page, size, data.getContent());

        res.setTotalPages(data.getTotalPages());
        res.setTotaltems(data.getTotalElements());

        return res;

    }

    @Transactional
    public Product save(Product product) {

        product.setId(Long.MIN_VALUE);

        return repository.save(product);

    }

    public Product update(long id, Product product) {
        product.setId(id);

        return repository.save(product);
    }

    public Product delete(long id) {

        Product product = findByid(id);

        repository.deleteById(id);

        return product;

    }

}
