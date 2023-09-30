/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package anderk222.stock.service;

import anderk222.stock.exception.ResourceNotFoundException;
import anderk222.stock.model.Pagination;
import anderk222.stock.model.Person;
import anderk222.stock.model.Provider;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("hasAuthority('PROVIDER_READ')")
    public Pagination<Provider> search(int page, int size, String value) {

        Pageable pageable = PageRequest.of(page, size);

        Page<Provider> data = repository
                .findByPersonNamesContainingIgnoreCase(value, pageable);

        Pagination<Provider> res = new Pagination<>(page, size, data.getContent());

        res.setNext(pageable.next().getPageNumber());
        res.setPrevious(pageable.hasPrevious() ? page - 1 : 1);

        res.setTotalPages(data.getTotalPages());
        res.setTotaltems(data.getTotalElements());

        return res;
    }

    @PreAuthorize("hasAuthority('PROVIDER_READ')")
    public Provider findByid(Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id, "id", "provider"));

    }

    @PreAuthorize("hasAuthority('PROVIDER_READ')")
    public List<Provider> findAll() {

        return repository.findAll();

    }

    @PreAuthorize("hasAuthority('PROVIDER_READ')")
    public Provider findByPersonId(Long id, int page, int size) {

        Person person = personService.findByid(id);

        Optional<Provider> optperson = repository.findByPersonId(id);

        if (optperson.isEmpty()) {

            Provider new_provider = new Provider();
            new_provider.setPerson(person);

            return repository.save(new_provider);
        }

        return optperson.get();
    }

    @PreAuthorize("hasAuthority('PROVIDER_WRITE')")
    public Provider save(Provider provider) {

        provider.setId(Long.MIN_VALUE);

        return repository.save(provider);

    }

    @PreAuthorize("hasAuthority('PROVIDER_WRITE')")
    public Provider update(Long id, Provider provider) {
        provider.setId(id);

        return repository.save(provider);
    }

    @PreAuthorize("hasAuthority('PROVIDER_WRITE')")
    public Provider delete(Long id) {

        Provider provider = findByid(id);

        repository.deleteById(id);

        return provider;

    }

}
