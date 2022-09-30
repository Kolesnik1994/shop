package shop.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import shop.com.model.Category;

public interface CategoryRepository extends JpaRepository <Category, Long>{

}
