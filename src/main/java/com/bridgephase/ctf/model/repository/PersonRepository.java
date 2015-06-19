package com.bridgephase.ctf.model.repository;

import org.springframework.data.repository.CrudRepository;

import com.bridgephase.ctf.model.jpa.Person;

public interface PersonRepository extends CrudRepository<Person, Long> {
}
