/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package anderk222.stock.service;

import anderk222.stock.exception.ResourceNotFoundException;
import anderk222.stock.model.Category;
import anderk222.stock.model.Pagination;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import anderk222.stock.repository.CategoryRepository;

/**
 *
 * @author tanki
 */

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    @PreAuthorize("hasAuthority('PRODUCT_READ')")
    public Pagination<Category> search(int page, int size, String value) {

        Pageable pageable = PageRequest.of(page, size);

        Page<Category> data = repository
                .findByNameContainingIgnoreCase(value, pageable);

        Pagination<Category> res = new Pagination<>(page, size, data.getContent());

        res.setNext(pageable.next().getPageNumber());
        res.setPrevious(pageable.hasPrevious() ? page - 1 : 1);

        res.setTotalPages(data.getTotalPages());
        res.setTotaltems(data.getTotalElements());

        return res;
    }

    @PreAuthorize("hasAuthority('PRODUCT_READ')")
    public List<Category> findAll() {

        return repository.findAll();

    }

    @PreAuthorize("hasAuthority('PRODUCT_READ')")
    public Category findByid(Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id, "id", "category"));

    }

    @PreAuthorize("hasAuthority('CATEGORY_WRITE')")
    public Category save(Category category) {

        category.setId(Long.MIN_VALUE);

        return repository.save(category);

    }

    @PreAuthorize("hasAuthority('CATEGORY_WRITE')")
    public Category update(Long id, Category category) {
        category.setId(id);

        return repository.save(category);
    }

    @PreAuthorize("hasAuthority('CATEGORY_WRITE')")
    public Category delete(Long id) {

        Category category = findByid(id);

        repository.deleteById(id);

        return category;

    }

}
