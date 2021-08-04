package com.codegym.demo.controller;

import com.codegym.demo.model.Category;
import com.codegym.demo.service.Category.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.GeneratedValue;
import java.util.Optional;

@Controller
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;
    @GetMapping("/create-category")
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView = new ModelAndView("/category/create");
        modelAndView.addObject("category", new Category());
        return modelAndView;
    }
    @PostMapping("/create-category")
    public ModelAndView saveCategory(@ModelAttribute("category") Category category){
        categoryService.save(category);
        ModelAndView modelAndView = new ModelAndView("category/create");
        modelAndView.addObject("category", new Category());
        modelAndView.addObject("message", "New category created successfully");
        return modelAndView;
    }
    @GetMapping("/categories")
    public ModelAndView listCategory() {
        ModelAndView modelAndView = new ModelAndView("/category/list");
        modelAndView.addObject("categories", categoryService.findAll());
        return modelAndView;
    }
    @GetMapping("/edit-category/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        Optional<Category> category = categoryService.findById(id);
        if (category.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/category/edit");
            modelAndView.addObject("category", category.get());
            return modelAndView;
        } else {
            return new ModelAndView("/error404");
        }
    }

    @PostMapping("/edit-category")
    public ModelAndView updateCategory(@ModelAttribute("category") Category category) {
        categoryService.save(category);
        ModelAndView modelAndView = new ModelAndView("/category/edit");
        modelAndView.addObject("category", category);
        modelAndView.addObject("message", "Category updated successfully");
        return modelAndView;
    }
    @GetMapping("/delete-category/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id) {
        Optional<Category> category = categoryService.findById(id);
        if (category.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/category/delete");
            modelAndView.addObject("category", category.get());
            return modelAndView;

        } else {
            return new ModelAndView("/error404");
        }
    }

    @PostMapping("/delete-category")
    public String deleteCustomer(@ModelAttribute("category") Category category) {
     categoryService.remove(category.getId());
        return "redirect:categories";
    }

}
