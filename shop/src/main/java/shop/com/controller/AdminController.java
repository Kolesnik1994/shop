package shop.com.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import shop.com.dto.ProductDTO;
import shop.com.model.Category;
import shop.com.model.Product;
import shop.com.service.CategoryService;
import shop.com.service.ProductService;

@Controller
public class AdminController {
	
	public static String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/img";
	
	@Autowired
	CategoryService catserv;
	
	@Autowired
	ProductService proserv;
	
	@GetMapping ("/admin")
	public String adminHome() {
		return "adminHome";
		
	}
	// get all list
	@GetMapping ("/admin/categories")
	public String getCategories(Model model) {
		model.addAttribute("categories", catserv.getAllCategory());
		return "categories";
	}
	
	// get form to add
	@GetMapping ("/admin/categories/add")
	public String getCatAdd (Model model) {
		model.addAttribute("category", new Category());
		return "categoriesAdd";
	}
	
	// create
	@PostMapping ("/admin/categories/add")
	public String postCatAdd (@ModelAttribute("category")Category category) {
		catserv.addCategory(category);
		return "redirect:/admin/categories";
	}
	
	// delete
	@GetMapping ("/admin/categories/delete/{id}")
	public String deleteCategry(@PathVariable Long id) {
		catserv.deleteById(id);
		return "redirect:/admin/categories";
		
	}
	
	// update
	@GetMapping ("/admin/categories/update/{id}")
	public String updateCategory(@PathVariable Long id, Model model) {
		Optional <Category> category = catserv.getById(id);
		if (category.isPresent()) {
			model.addAttribute("category", category.get());
			return "categoriesAdd";
		}
		else return "404";
			
	}
   
	// product Section
	
	@GetMapping ("/admin/products")
	public String deleteProduct(Model model) {
		model.addAttribute("products", proserv.getall());
		return "products";
	}
	
	@GetMapping ("admin/products/add")
	public String productAdd (Model model) {
		model.addAttribute("productDTO", new ProductDTO());
		model.addAttribute("categories", catserv.getAllCategory());
		return "productsAdd";
		
	}
	
	@PostMapping("/admin/products/add")
	public String productAddPost(@ModelAttribute ("productDTO") ProductDTO productDTO,
			                     @RequestParam("productImage") MultipartFile file, 
			                     @RequestParam ("imgName")String imgName) throws IOException {
		
		Product product = new Product();
		product.setId(productDTO.getId());
		product.setName(productDTO.getName());
		product.setCategory(catserv.getById(productDTO.getCategoryId()).get());
		product.setPrice(productDTO.getPrice());
		product.setWeight(productDTO.getWeight());
		product.setDescription(productDTO.getDescription());
		String imageUUID;
		if(!file.isEmpty()) {
			imageUUID = file.getOriginalFilename();
			Path fileNameAndPath = Paths.get(uploadDir, imageUUID);
			Files.write(fileNameAndPath, file.getBytes());
		}else {
			imageUUID = imgName;
		}
		product.setImageName(imageUUID);
		proserv.addProduct(product);
		return "redirect:/admin/products";
		
	}
	
	@GetMapping ("/admin/product/delete/{id}")
	public String deleteProduct(@PathVariable Long id) {
		proserv.removeProduct(id);
		return "redirect:/admin/products";
		
	}
	
	@GetMapping ("/admin/product/update/{id}")
	public String updateProduct (@PathVariable Long id, Model model) {
		Product product = proserv.getProductById(id).get();
		ProductDTO productDTO = new ProductDTO();
		productDTO.setId(productDTO.getId());
		productDTO.setName(productDTO.getName());
		productDTO.setCategoryId(productDTO.getCategoryId());
		productDTO.setPrice(productDTO.getPrice());
		productDTO.setWeight((productDTO.getWeight()));
		productDTO.setDescription(productDTO.getDescription());
		productDTO.setImageName(productDTO.getImageName());
		
		model.addAttribute("categories", catserv.getAllCategory());
		model.addAttribute("productDTO", productDTO);
		
			return "productsAdd";
	
			
	
	
	
	}
}
