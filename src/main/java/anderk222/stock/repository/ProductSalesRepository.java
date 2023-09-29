/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package anderk222.stock.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import anderk222.stock.model.ProductSales;


/**
 *
 * @author tanki
 */
public interface ProductSalesRepository extends JpaRepository<ProductSales, Long> {

    List<ProductSales> findBySalesId(Long id); 
    
}