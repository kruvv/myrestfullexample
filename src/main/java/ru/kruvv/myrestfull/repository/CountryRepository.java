package ru.kruvv.myrestfull.repository;

import org.springframework.data.repository.CrudRepository;

import ru.kruvv.myrestfull.domain.Country;

public interface CountryRepository extends CrudRepository<Country, Integer> {

}
