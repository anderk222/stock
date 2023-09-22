/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package anderk222.stock.service;

import anderk222.stock.exception.ResourceNotFoundException;
import anderk222.stock.model.Pagination;
import anderk222.stock.model.Person;
import anderk222.stock.model.Provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import anderk222.stock.repository.ProviderRepository;
import java.util.Optional;

/**
 *
 * @author tanki
 */
@Service
public class ProviderService {

    @Autowired
    private ProviderRepository repository;
    
    @Autowired 
    private PersonService personService;

    public Pagination<Provider> search(int page, int size, String value) {

        Pageable pageable = PageRequest.of(page, size);

        Page<Provider> data = repository
                .findByPersonNamesContainingIgnoreCase(value, pageable);

        Pagination<Provider> res = new Pagination<>(page, size, data.getContent());

        res.setTotalPages(data.getTotalPages());
        res.setTotaltems(data.getTotalElements());

        return res;
    }

    public Provider findByid(long id) {

        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id, "id", "provider"));

    }

    public Provider findByPersonId(long id, int page, int size) {
        
        Person person =  personService.findByid(id);
        
        Optional<Provider> optperson = repository.findByPersonId(id);

        if(optperson.isEmpty()){
            
            Provider new_provider =  new Provider();
            new_provider.setPerson(person);
            
            return repository.save(new_provider);
        }
        
        return optperson.get();
    }

    public Provider save(Provider provider) {

        provider.setId(Long.MIN_VALUE);

        return repository.save(provider);

    }

    public Provider update(long id, Provider provider) {
        provider.setId(id);

        return repository.save(provider);
    }

    public Provider delete(long id) {

        Provider provider = findByid(id);

        repository.deleteById(id);

        return provider;

    }

}
