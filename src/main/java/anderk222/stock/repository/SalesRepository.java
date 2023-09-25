/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package anderk222.stock.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import anderk222.stock.model.Sales;
import anderk222.stock.model.SalesProjection;

/**
 *
 * @author tanki
 */
public interface SalesRepository extends JpaRepository<Sales, Long> { 

    
    @Query(value ="SELECT sales.id as id, sales.invoice as invoice, person FROM Sales as sales JOIN Person as person on person.id=sales.customer WHERE person.id=:person"
    , nativeQuery = true)
    Page<SalesProjection> findByPersonId(@Param("person") long person, Pageable pageable);    
   
}