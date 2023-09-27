/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package anderk222.stock.service;

import anderk222.stock.exception.ResourceNotFoundException;
import anderk222.stock.model.Pagination;
import anderk222.stock.model.Person;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import anderk222.stock.repository.PersonRepository;

/**
 *
 * @author tanki
 */

@Service
public class PersonService {

    @Autowired
    private PersonRepository repository;
    
    public Pagination<Person> search(int page, int size,String value){
        
        Pageable pageable = PageRequest.of(page, size);
        
        Page<Person> data = repository
                .findByNamesContainingIgnoreCase(value,pageable);
        
        Pagination<Person> res = new Pagination<>(page, size, data.getContent());
        
        res.setTotalPages(data.getTotalPages());
        res.setTotaltems(data.getTotalElements());
        
        return res;
    }

    public List<Person> findAll(){

        return repository.findAll();
    }
    
    public Person findByid(long id){
    
        return repository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException(id, "id", "person"));
        
    }
    
    public Person save(Person person){
        
        person.setId(Long.MIN_VALUE);
        
        return repository.save(person);
        
    }
    
    public Person update(long id,Person person){
        person.setId(id);
        
        return repository.save(person);
    }
    
    public Person delete(long id){
        
        Person person = findByid(id);
        
        repository.deleteById(id);
        
        return person;
        
    }
    
}
