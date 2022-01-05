package com.neosoft.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.neosoft.springboot.service.InvalidRequestException;
import com.neosoft.springboot.service.UserService;
import com.neosoft.springboot.model.User;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

	
	@Autowired
	private UserService userservice;
	
	
	@GetMapping("/users")
	private List<User> getUsers(){
		return userservice.getAllUsers(); 
	}
	
	//get soft deleted users
	
	@GetMapping("/softDeletedUsers")
	private List<User> getSoftDeleted(){
		return userservice.getSoftDeleted();
	}
	
	@GetMapping("/usersbyname/{name}")
	private List<User> getUsersByName(@PathVariable String name){
		return userservice.getUsersByName(name);
	}
	
	
	@GetMapping("/usersbysurname/{surname}")
	private List<User> getUsersBySurName(@PathVariable String surname){
		return userservice.getUsersBySurName(surname);
	}
	
	@GetMapping("/usersbypincode/{pincode}")
	private List<User> getUsersByPincode(@PathVariable long pincode){
		return userservice.getUsersByPincode(pincode);
	}
	
	@GetMapping("/sort/sortbyDob")
	public List<User> sortByDob( User user) {
		return userservice.sortByDob();
	}
	
	@GetMapping("/sort/sortbyDoj")
	public List<User> sort( User user) {
		return userservice.sortByDoj();
	}
	
	@PostMapping("/add/users")
	private User addUsers(@RequestBody User user){
		return userservice.addUsers(user);
	}
	

	//Hard delete
	@DeleteMapping("/delete/users/{id}")
	public void delete(@PathVariable  int id) {
		if(userservice.getUsersById(id).isPresent()) {
			userservice.deleteUsersById(id);   
		}
		else{
			throw new InvalidRequestException("User with ID " + 
        			id + " does not exist.");
		}
			
	}
	
	//Soft delete
	@DeleteMapping("/softdelete/user/{id}")
	public void softdelete(@PathVariable  int id) {
		if(userservice.getUsersById(id).isPresent()) {
			userservice.softDeleteUsersById(id);
		}
		else{
			throw new InvalidRequestException("User with ID " + 
        			id + " does not exist.");
		}
	}
	
	
	@PutMapping("/update/users/{id}")
	public User updateInfo(@RequestBody User user , @PathVariable int id) {
		if(userservice.getUsersById(id).isPresent()) {
			user.setId(id);
			return userservice.update(user);
		}
		else {
			throw new InvalidRequestException("User with ID " + 
        			user.getId() + " does not exist.");
		}	
	}
}
