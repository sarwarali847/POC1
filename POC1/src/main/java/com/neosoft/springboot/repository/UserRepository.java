package com.neosoft.springboot.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.neosoft.springboot.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

	List<User> findBySurname(String surname);

	List<User> findByPincode(long pincode);

	List<User> findByName(String name);

	@Transactional
	@Modifying
	@Query("SELECT u from User u where u.deleted=false")
	List<User> getPresentUsers();

	@Transactional
	@Modifying
	@Query("SELECT s from User s where s.deleted=true")
	List<User> getSoftDeletedUsers();

	@Transactional
	@Modifying
	@Query("UPDATE User c SET c.deleted=true WHERE c.id=:uid")
	Object softDelete(@Param("uid") int id);

}
