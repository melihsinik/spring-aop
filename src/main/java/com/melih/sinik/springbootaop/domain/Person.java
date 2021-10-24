package com.melih.sinik.springbootaop.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Melih ŞİNİK
 * @since 13.10.2021
 */
@Getter
@Setter
@Entity
@ToString
@Table(name = "person")
public class Person extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "last_name")
    private String lastName;
}
