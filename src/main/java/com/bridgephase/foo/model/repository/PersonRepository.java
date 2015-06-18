package com.bridgephase.foo.model.repository;

import org.springframework.data.repository.CrudRepository;

import com.bridgephase.foo.model.jpa.Person;

public interface PersonRepository extends CrudRepository<Person, Long> {
}
