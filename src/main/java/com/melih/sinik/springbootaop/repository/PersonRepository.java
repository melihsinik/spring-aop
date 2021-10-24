package com.melih.sinik.springbootaop.repository;

import com.melih.sinik.springbootaop.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Melih ŞİNİK
 * @since 13.10.2021
 */
@Repository
public interface PersonRepository extends JpaRepository<Person, String> {
    Person getById(String id);
}
