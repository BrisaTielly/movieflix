package br.com.movieflix.movieflix.controller;

import br.com.movieflix.movieflix.entity.Category;
import br.com.movieflix.movieflix.service.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/movieflix/category")
public class CategoryController {
    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public List<Category> getAllCategories(){
        return service.findAll();
    }

}
