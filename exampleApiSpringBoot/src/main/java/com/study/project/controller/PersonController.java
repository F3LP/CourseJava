package com.study.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.study.project.entities.Person;
import com.study.project.services.PersonServices;

@RestController
@RequestMapping(value = "/person")
public class PersonController {

	@Autowired
	private PersonServices personServices;

	@GetMapping
	public ResponseEntity<List<Person>> responseEntity() {
		List<Person> list = personServices.findAll();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Person> findById(@PathVariable long id) {
		Person obj = personServices.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	@PostMapping
	public Person create(@RequestBody Person person) {
		return personServices.create(person);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Person> update(@PathVariable Long id, @RequestBody Person person){
		person = personServices.update(id, person);
		return ResponseEntity.ok().body(person);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		personServices.delete(id);
		return ResponseEntity.noContent().build();
	}

}
