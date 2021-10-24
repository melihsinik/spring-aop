package com.melih.sinik.springbootaop.service;

import com.melih.sinik.springbootaop.domain.Person;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author Melih ŞİNİK
 * @since 13.10.2021
 */
@Validated
public interface PersonService {

    Person create(@Valid @NotNull Person person);
    void delete(@Valid @NotNull Person person);
    Person getById(@NotEmpty String id);
    List<Person> getAll();
}
