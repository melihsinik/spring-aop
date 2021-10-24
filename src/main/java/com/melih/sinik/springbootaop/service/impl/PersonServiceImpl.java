package com.melih.sinik.springbootaop.service.impl;

import com.melih.sinik.springbootaop.domain.Person;
import com.melih.sinik.springbootaop.repository.PersonRepository;
import com.melih.sinik.springbootaop.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Melih ŞİNİK
 * @since 13.10.2021
 */
@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    @Override
    @Transactional
    public Person create(Person person) {
        return personRepository.save(person);
    }

    @Override
    @Transactional
    public void delete(Person person) {
        personRepository.delete(person);
    }

    @Override
    public Person getById(String id) {
        return personRepository.getById(id);
    }

    @Override
    public List<Person> getAll() {
        return personRepository.findAll();
    }

}
