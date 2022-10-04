package shop.com.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shop.com.model.Product;
import shop.com.repository.ProductRepositories;

@Service
public class ProductService {
	
	@Autowired
	ProductRepositories prorep;

	public List <Product> getall() { 
		return prorep.findAll();}
	
	public void addProduct (Product product) {
		prorep.save(product);
	}
	
	public void removeProduct (Long id) {
		prorep.deleteById(id);
	}
	
	public Optional <Product> getProductById(Long id) {
		return prorep.findById(id);
	}
	
	public List<Product> getAllProductsByCategoryId (Long id) {
		return prorep.findAllByCategory_Id(id);
	}
	
	
}
