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
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("hasAuthority('PERSON_READ')")
    public Pagination<Person> search(int page, int size, String value) {

        Pageable pageable = PageRequest.of(page, size);

        Page<Person> data = repository
                .findByNamesContainingIgnoreCase(value, pageable);

        Pagination<Person> res = new Pagination<>(page, size, data.getContent());

        res.setNext(pageable.next().getPageNumber());
        res.setPrevious(pageable.hasPrevious() ? page - 1 : 1);

        res.setTotalPages(data.getTotalPages());
        res.setTotaltems(data.getTotalElements());

        return res;
    }

    @PreAuthorize("hasAuthority('PERSON_READ')")
    public List<Person> findAll() {

        return repository.findAll();
    }

    @PreAuthorize("hasAuthority('PERSON_READ')")
    public Person findByid(Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id, "id", "person"));

    }

    @PreAuthorize("hasAuthority('PERSON_WRITE')")
    public Person save(Person person) {

        person.setId(Long.MIN_VALUE);

        return repository.save(person);

    }

    @PreAuthorize("hasAuthority('PERSON_WRITE')")
    public Person update(Long id, Person person) {
        person.setId(id);

        return repository.save(person);
    }

    @PreAuthorize("hasAuthority('PERSON_WRITE')")
    public Person delete(Long id) {

        Person person = findByid(id);

        repository.deleteById(id);

        return person;

    }

}
