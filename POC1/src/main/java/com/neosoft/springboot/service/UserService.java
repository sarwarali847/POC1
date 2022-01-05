package com.neosoft.springboot.service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neosoft.springboot.model.User;
import com.neosoft.springboot.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userrepo;
	
	
	
	//Get all user
	public List<User> getAllUsers(){
		return userrepo.findAll();
	}
	
	
	//Add user
	public User addUsers(User user) {
		return userrepo.save(user);
	}

	//Delete user by id
	public void deleteUsersById(int id) {
		userrepo.deleteById(id);
		
	}

	//Get user by id
	public Optional<User> getUsersById(int id) {
		// TODO Auto-generated method stub
		return userrepo.findById(id);
	}

	//Update user by id
	public User update(User user) {
		// TODO Auto-generated method stub
		return userrepo.save(user);
	}

	//Get user by user name
	public List<User> getUsersByName(String name) {
		// TODO Auto-generated method stub
		return userrepo.findByName(name);
	}

	public List<User> getUsersBySurName(String surname) {
		// TODO Auto-generated method stub
		return userrepo.findBySurname(surname);
	}

	public List<User> getUsersByPincode(long pincode) {
		// TODO Auto-generated method stub
		return userrepo.findByPincode(pincode);
	}
	
	
	public List<User> sortByDoj() {
		
	 return userrepo.findAll().stream().sorted((o1, o2) -> o1.getDoj().
				compareTo(o2.getDoj())).collect(Collectors.toList());
		
	}
	
	public List<User> sortByDob() {
		
		 return userrepo.findAll().stream().sorted((o1, o2) -> o1.getDob().
					compareTo(o2.getDob())).collect(Collectors.toList());
			
		}


	public List<User> getUsers() {
		return userrepo.getPresentUsers();
	}


	public List<User> getSoftDeleted() {
		
		return userrepo.getSoftDeletedUsers();
	}


	public Object softDeleteUsersById(int id) {
		return userrepo.softDelete(id);
		
	}

}
