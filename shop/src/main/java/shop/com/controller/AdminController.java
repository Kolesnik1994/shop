package shop.com.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import shop.com.model.Category;
import shop.com.service.CategoryService;

@Controller
public class AdminController {
	
	@Autowired
	CategoryService catserv;
	
	@GetMapping ("/admin")
	public String adminHome() {
		return "adminHome";
		
	}
	
	@GetMapping ("/admin/categories")
	public String getCategories(Model model) {
		model.addAttribute("categories", catserv.getAllCategory());
		return "categories";
	}
	
	
	@GetMapping ("/admin/categories/add")
	public String getCatAdd (Model model) {
		model.addAttribute("category", new Category());
		return "categoriesAdd";
	}
	
	@PostMapping ("/admin/categories/add")
	public String postCatAdd (@ModelAttribute("category")Category category) {
		catserv.addCategory(category);
		return "redirect:/admin/categories";
	}
	
	// DELETE
	@GetMapping ("/admin/categories/delete/{id}")
	public String deleteCategry(@PathVariable Long id) {
		catserv.deleteById(id);
		return "redirect:/admin/categories";
		
	}
	
	@GetMapping ("/admin/categories/update/{id}")
	public String updateCategory(@PathVariable Long id, Model model) {
		Optional <Category> category = catserv.getById(id);
		if (category.isPresent()) {
			model.addAttribute("category", category.get());
			return "categoriesAdd";
		}
		else return "404";
			
	}

}
