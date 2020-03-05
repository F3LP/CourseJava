package com.study.project.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.project.entities.Person;
import com.study.project.repository.PersonRepository;

@Service
public class PersonServices {

	@Autowired
	private PersonRepository personRepository;
	
	public List<Person> findAll(){
		return personRepository.findAll();
	}
	
	public Person findById(Long id) {
		Optional<Person> person = personRepository.findById(id);
		return person.get();
	}
	
	public Person create(Person person) {
		return personRepository.save(person);
	}
	
	public Person update(Long id, Person person) {
		Person p1 = personRepository.getOne(id);
		p1.setId(person.getId());
		p1.setName(person.getName());
		
		return personRepository.save(p1);
	}
	
	public void delete(Long id) {
		personRepository.deleteById(id);
	}
	
}
