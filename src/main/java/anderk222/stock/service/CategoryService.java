/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package anderk222.stock.service;

import anderk222.stock.exception.ResourceNotFoundException;
import anderk222.stock.model.Category;
import anderk222.stock.model.Pagination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    
    public Pagination<Category> search(int page, int size,String value){
        
        Pageable pageable = PageRequest.of(page, size);
        
        Page<Category> data = repository
                .findByNameContainingIgnoreCase(value,pageable);
        
        Pagination<Category> res = new Pagination<>(page, size, data.getContent());
        
        res.setTotalPages(data.getTotalPages());
        res.setTotaltems(data.getTotalElements());
        
        return res;
    }
    
    public Category findByid(long id){
    
        return repository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException(id, "id", "category"));
        
    }
    
    public Category save(Category category){
        
        category.setId(Long.MIN_VALUE);
        
        return repository.save(category);
        
    }
    
    public Category update(long id,Category category){
        category.setId(id);
        
        return repository.save(category);
    }
    
    public Category delete(long id){
        
        Category category = findByid(id);
        
        repository.deleteById(id);
        
        return category;
        
    }
    
}
