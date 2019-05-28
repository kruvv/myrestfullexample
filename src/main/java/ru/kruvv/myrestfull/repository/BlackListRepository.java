package ru.kruvv.myrestfull.repository;

import org.springframework.data.repository.CrudRepository;

import ru.kruvv.myrestfull.domain.BlackList;
import ru.kruvv.myrestfull.domain.Person;

public interface BlackListRepository extends CrudRepository<BlackList, Integer> {
	BlackList findByPerson(Person person);
}
