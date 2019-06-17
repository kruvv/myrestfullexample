package ru.kruvv.myrestfull.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.kruvv.myrestfull.domain.Person;
import ru.kruvv.myrestfull.repository.BlackListRepository;

/**
 * Если клиент находится в черном списке запрещаем ему создавать ссуду
 * 
 * @author viktor
 *
 */

@Service
public class BlackListServiceImpl implements BlackListService {

	private final BlackListRepository repository;

	@Autowired
	public BlackListServiceImpl(BlackListRepository repository) {
		this.repository = repository;
	}

	@Override
	public boolean isBlackListPerson(int personId) {
		return this.repository.findByPerson(new Person(personId)) != null;
	}

}
