package shop.com.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shop.com.model.Category;
import shop.com.repository.CategoryRepository;

@Service
public class CategoryService {
	
	@Autowired
	CategoryRepository catrepo;
	
	public List <Category> getAllCategory() {
		return catrepo.findAll();
	}
	
	public void addCategory (Category category) {
		catrepo.save(category);
	}
	
	public void deleteById (Long id) {
		catrepo.deleteById(id);}

	
    public Optional <Category> getById (Long id) {
    	return catrepo.findById(id);}
    }
  

