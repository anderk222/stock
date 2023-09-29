/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package anderk222.stock.repository;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import anderk222.stock.model.Provider;

/**
 *
 * @author tanki
 */
public interface ProviderRepository extends JpaRepository<Provider, Long>  {

    Optional<Provider> findByPersonId(Long id);
    
    Page<Provider> findByPersonNamesContainingIgnoreCase(String names, Pageable pageable);
    
    
    
}