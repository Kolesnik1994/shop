package shop.com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import shop.com.model.Product;

public interface ProductRepositories extends JpaRepository <Product, Long>{
	
	List<Product> findAllByCategory_Id(Long id);

}
