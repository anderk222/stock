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

import anderk222.stock.dto.SalesProjection;
import anderk222.stock.model.Sales;

/**
 *
 * @author tanki
 */
public interface SalesRepository extends JpaRepository<Sales, Long> { 

    
    @Query(value ="SELECT sales.*, person.id as id_person,person.names,person.lastnames FROM Sales as sales JOIN Person as person on person.id=sales.person_id WHERE person.id=:person"
    , nativeQuery = true)
    Page<SalesProjection> findByPersonId(@Param("person") Long person, Pageable pageable);    
   
} 