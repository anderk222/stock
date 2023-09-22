/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package anderk222.stock.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import anderk222.stock.model.*;

/**
 *
 * @author tanki
 */

public interface CategoryRepository extends JpaRepository<Category, Long>{

    Page<Category> findByNameContainingIgnoreCase(String name, Pageable pageable);
    
}
