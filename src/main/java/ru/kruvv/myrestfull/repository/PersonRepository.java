package ru.kruvv.myrestfull.repository;

import org.springframework.data.repository.CrudRepository;

import ru.kruvv.myrestfull.domain.Person;

public interface PersonRepository extends CrudRepository<Person, Integer> {

}
