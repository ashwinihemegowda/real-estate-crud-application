package com.thbs.realestate.repository;

import com.thbs.realestate.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{

	//Abstract method to get user filtered by email
	public User findByEmail(String email);
	public Boolean existsByEmail(String email);
}
