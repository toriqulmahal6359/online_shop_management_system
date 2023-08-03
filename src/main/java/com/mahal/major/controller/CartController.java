package com.mahal.major.controller;

import com.mahal.major.global.GlobalData;
import com.mahal.major.model.Product;
import com.mahal.major.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CartController {
    @Autowired
    ProductService productService;
    @GetMapping("/addToCart/{id}")
    public String addToCart(@PathVariable int id, Model model){
        GlobalData.cart.add(productService.getProductsById(id).get());
        model.addAttribute("cartCount", GlobalData.cart.size());
        return "redirect:/shop";
    }
    @GetMapping("/cart")
    public String getCart(Model model){
        model.addAttribute("cartCount", GlobalData.cart.size());
        model.addAttribute("total", GlobalData.cart.stream().mapToDouble(Product::getPrice).sum());
        model.addAttribute("cart", GlobalData.cart);
        return "cart";
    }
    @GetMapping("/cart/removeItem/{index}")
    public String cartItemRemove(@PathVariable int index, Model model){
        GlobalData.cart.remove(index);
        model.addAttribute("cartCount", GlobalData.cart.size());
        return "redirect:/cart";
    }
    @GetMapping("/checkout")
    public String checkout(Model model){
        model.addAttribute("cartCount", GlobalData.cart.size());
        model.addAttribute("total", GlobalData.cart.stream().mapToDouble(Product::getPrice).sum());
        return "checkout";
    }

}
