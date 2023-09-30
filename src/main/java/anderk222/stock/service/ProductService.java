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
import org.springframework.security.access.prepost.PreAuthorize;
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

    // @PreAuthorize("hasAuthority('PRODUCT_READ')")
    public Pagination<Product> search(int page, int size, String value) {

        Pageable pageable = PageRequest.of(page, size);

        Page<Product> data = repository
                .findByNameContainingIgnoreCase(value, pageable);

        Pagination<Product> res = new Pagination<>(page, size, data.getContent());

        res.setNext(pageable.next().getPageNumber());
        res.setPrevious(pageable.hasPrevious() ? page - 1 : 1);

        res.setTotalPages(data.getTotalPages());
        res.setTotaltems(data.getTotalElements());

        return res;
    }

    // @PreAuthorize("hasAuthority('PRODUCT_READ')")
    public Product findByid(Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id, "id", "product"));

    }

    // @PreAuthorize("hasAuthority('PRODUCT_READ')")
    public Pagination<Product> findByProviderId(Long id, int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        Page<Product> data = repository
                .findByDetailProviderId(id, pageable);

        Pagination<Product> res = new Pagination<>(page, size, data.getContent());

        res.setNext(pageable.next().getPageNumber());
        res.setPrevious(pageable.hasPrevious() ? page - 1 : 1);

        res.setTotalPages(data.getTotalPages());
        res.setTotaltems(data.getTotalElements());

        return res;

    }

    // @PreAuthorize("hasAuthority('PRODUCT_READ')")
    public Pagination<Product> findByCategoryId(Long id, int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        Page<Product> data = repository
                .findByDetailCategoryId(id, pageable);

        Pagination<Product> res = new Pagination<>(page, size, data.getContent());

        res.setTotalPages(data.getTotalPages());
        res.setTotaltems(data.getTotalElements());

        return res;

    }

    @Transactional
    @PreAuthorize("hasAuthority('PRODUCT_WRITE')")
    public Product save(Product product) {

        product.setId(Long.MIN_VALUE);

        return repository.save(product);

    }

    @PreAuthorize("hasAuthority('PRODUCT_WRITE')")
    public Product update(Long id, Product product) {
        product.setId(id);

        return repository.save(product);
    }

    @PreAuthorize("hasAuthority('PRODUCT_WRITE')")
    public Product delete(Long id) {

        Product product = findByid(id);

        repository.deleteById(id);

        return product;
    }

}