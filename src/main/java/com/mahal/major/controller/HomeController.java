package com.mahal.major.controller;

import com.mahal.major.global.GlobalData;
import com.mahal.major.service.CategoryService;
import com.mahal.major.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;

    @GetMapping({"/", "/home"})
    public String home(Model model){
        model.addAttribute("cartCount", GlobalData.cart.size());
        return "index";
    }
    @GetMapping("/shop")
    public String shop(Model model){
        model.addAttribute("cartCount", GlobalData.cart.size());
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("products", productService.getAllProducts());
        return "shop";
    }

    @GetMapping("/shop/category/{id}")
    public String shopByCategory(Model model, @PathVariable int id){
        model.addAttribute("cartCount", GlobalData.cart.size());
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("products", productService.getAllProductsByCategoryId(id));
        return "shop";
    }
    @GetMapping("/shop/viewproduct/{id}")
    public String viewProduct(Model model, @PathVariable int id){
        model.addAttribute("cartCount", GlobalData.cart.size());
        model.addAttribute("product", productService.getProductsById(id).get());
        return "viewProduct";
    }

}
