package shop.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import shop.com.model.Role;

public interface RoleRepository extends JpaRepository <Role, Long> {

}
