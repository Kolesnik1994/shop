package shop.com.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import shop.com.model.User;

public interface UserRepository extends JpaRepository  <User, Long>{
	
	Optional <User> findByEmail (String email);


	

	
}
