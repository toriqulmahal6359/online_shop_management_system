package com.mahal.major.controller;

import com.mahal.major.dto.ProductDTO;
import com.mahal.major.model.Category;
import com.mahal.major.model.Product;
import com.mahal.major.service.CategoryService;
import com.mahal.major.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Controller
public class AdminController {
	@Autowired
	CategoryService categoryService;
	@Autowired
	ProductService productService;

	@GetMapping("/admin")
	public String adminHome() {
		return "adminHome";
	}

	//Category Section
	@GetMapping("/admin/categories")
	public String getCategory(Model model){
		model.addAttribute("categories", categoryService.getAllCategories());
		return "categories";
	}
	@GetMapping("/admin/categories/add")
	public String addCategory(Model model){
		model.addAttribute("category", new Category());
		return "categoriesAdd";
	}
	@PostMapping("/admin/categories/add")
	public String postAddCategory(@ModelAttribute("category") Category category){
		categoryService.addCategory(category);
		return "redirect:/admin/categories";
	}
	@GetMapping("/admin/categories/delete/{id}")
	public String deleteCategory(@PathVariable int id){
		categoryService.deleteCategoryById(id);
		return "redirect:/admin/categories";
	}
	@GetMapping("/admin/categories/update/{id}")
	public String updateCategory(@PathVariable int id, Model model){
		Optional<Category> category = categoryService.getCategoryById(id);
		if(category.isPresent()){
			model.addAttribute("category", category.get());
			return "categoriesAdd";
		}else{
			return "404";
		}
	}

	//Product Section
	@GetMapping("/admin/products")
	public String getProducts(Model model){
		model.addAttribute("products", productService.getAllProducts());
		return "products";
	}

	@GetMapping("/admin/products/add")
	public String addProduct(Model model){
		model.addAttribute("productDTO", new ProductDTO());
		model.addAttribute("categories", categoryService.getAllCategories());
		return "productsAdd";
	}

    @PostMapping("/admin/products/add")
    public String postAddProduct(@ModelAttribute("productDTO") ProductDTO productDTO,
								 @RequestParam("productImage")MultipartFile file,
								 @RequestParam("imgName")String imgName) throws IOException {
		Product product = new Product();
		product.setId(productDTO.getId());
		product.setName(productDTO.getName());
		product.setCategory(categoryService.getCategoryById(productDTO.getCategoryId()).get());
		product.setPrice(productDTO.getPrice());
		product.setDescription(productDTO.getDescription());
		product.setWeight(productDTO.getWeight());
		return "redirect:/admin/products";
	}
}
