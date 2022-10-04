package shop.com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import shop.com.service.CategoryService;
import shop.com.service.ProductService;

@Controller
public class HomeController {
	@Autowired
	CategoryService catserv;
	@Autowired
	ProductService proserv;
	
	@GetMapping ({"/", "/home"})
	public String home (Model model) {
		return "index";
	}
	
	
	@GetMapping ("/shop")
	public String shop (Model model) {
		model.addAttribute("categories", catserv.getAllCategory());
		model.addAttribute ("products", proserv.getall());
		return "shop";
	}
	
	@GetMapping ("/shop/category/{id}")
	public String shopByCategory (Model model, @PathVariable Long id) {
		model.addAttribute("categories", catserv.getAllCategory());
		model.addAttribute ("products", proserv.getAllProductsByCategoryId(id));
		return "shop";
	}
	
	@GetMapping ("/shop/viewproduct/{id}")
	public String viewById (Model model, @PathVariable Long id) {			
	    model.addAttribute("product", proserv.getProductById(id).get());
	     return "viewproduct";
}
}
