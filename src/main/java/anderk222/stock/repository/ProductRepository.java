/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package anderk222.stock.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import anderk222.stock.model.Product;

/**
 *
 * @author tanki
 */
public interface ProductRepository extends JpaRepository<Product, Long>{

    Page<Product> findByNameContainingIgnoreCase(String name, Pageable pageable);
    
    Page<Product> findByDetailCategoryId(long id, Pageable pageable);
    
    Page<Product> findByDetailProviderId(long id, Pageable pageable);
    
}