package com.study.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.study.project.entities.User;
import com.study.project.services.UserServices;

@RestController
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private UserServices userServices;
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<User> findById(@PathVariable long id){
		User obj = userServices.findById(id);
		
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping
	public ResponseEntity<List<User>> findAll(){
		List<User> list = userServices.findAll();
		
		return ResponseEntity.ok().body(list);	
	}
	
	@PostMapping
	public ResponseEntity<User> create(@RequestBody User user){
		userServices.create(user);
		
		return ResponseEntity.ok().body(user);
	}
	
	
}
