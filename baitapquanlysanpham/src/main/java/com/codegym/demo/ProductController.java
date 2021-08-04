package com.codegym.demo;

import com.codegym.demo.model.Category;
import com.codegym.demo.model.Product;
import com.codegym.demo.repository.ICategoryRepository;
import com.codegym.demo.service.Category.ICategoryService;
import com.codegym.demo.service.Product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.awt.print.Pageable;
import java.util.Optional;

@Controller
public class ProductController {
    @Autowired
    private IProductService productService;
    @Autowired
    private ICategoryService categoryService;
    @ModelAttribute("categories")
    public Iterable<Category> provinces(){
        return categoryService.findAll();
    }
    @GetMapping("/create-product")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/product/create");
        modelAndView.addObject("product", new Product());
        return modelAndView;
    }
    @PostMapping("/create-product")
    public ModelAndView saveCustomer(Product product) {
        productService.save(product);
        ModelAndView modelAndView = new ModelAndView("/product/create");
        modelAndView.addObject("product", new Product());
        modelAndView.addObject("message", "New customer created successfully");
        return modelAndView;
    }
    @GetMapping("/products")
    public ModelAndView listProduct() {
        ModelAndView modelAndView = new ModelAndView("/product/list");
        modelAndView.addObject("products", productService.findAll());
        return modelAndView;
    }
    @GetMapping("/edit-product/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        Optional<Product> products = productService.findById(id);
        if (products.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/product/edit");
            modelAndView.addObject("product", products.get());
            return modelAndView;
        } else {
            ModelAndView modelAndView = getModelAndView();
            return modelAndView;
        }
    }
    private ModelAndView getModelAndView() {
        return new ModelAndView("/error404");
    }
    @PostMapping("/edit-product")
    public ModelAndView updateProduct(@ModelAttribute("product") Product product) {
        productService.save(product);
        ModelAndView modelAndView = new ModelAndView("/product/edit");
        modelAndView.addObject("product", product);
        modelAndView.addObject("message", "Customer updated successfully");
        return modelAndView;
    }
    @GetMapping("/delete-product/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id) {
        Optional<Product> product = productService.findById(id);
        if (product.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/product/delete");
            modelAndView.addObject("product", product.get());
            return modelAndView;

        } else {
            ModelAndView modelAndView = new ModelAndView("/error404");
            return modelAndView;
        }
    }
    @PostMapping("/delete-product")
    public String deleteCustomer(@ModelAttribute("product") Product product) {
        productService.remove(product.getId());
        return "redirect:products";
    }


}
