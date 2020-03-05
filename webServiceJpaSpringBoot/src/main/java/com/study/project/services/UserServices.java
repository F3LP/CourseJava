package com.study.project.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.project.entities.User;
import com.study.project.repository.UserRepository;

@Service
public class UserServices {

	@Autowired
	private UserRepository userRepository;
	
	
	public User findById(Long id) {
		Optional<User> obj = userRepository.findById(id);
		
		return obj.get();
	}
	
	public List<User> findAll(){
		return userRepository.findAll();
	}
	
	public void create(User user) {
		userRepository.save(user);
	}
	
//	public void update(long id, User user) {
//		Optional<User> obj = userRepository.findById(id).map(temp -> {
//															temp.setId(user.getId()); 
//															temp.setName(user.getName());
//															temp.setEmail(user.getEmail());
//															temp.setPhone(user.getPhone());
//															temp.setPassword(user.getPassword());
//															
//		userRepository.save(temp);
//															
//		});
//	}
}






