package br.com.movieflix.movieflix.controller;

import br.com.movieflix.movieflix.entity.Category;
import br.com.movieflix.movieflix.repository.CategoryRepository;
import br.com.movieflix.movieflix.request.CategoryRequest;
import br.com.movieflix.movieflix.response.CategoryResponse;
import br.com.movieflix.movieflix.service.CategoryService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movieflix/category")
public class CategoryController {
    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @GetMapping("/list")
    public ResponseEntity<List<CategoryResponse>> findAll(){
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(service.findAll());
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<CategoryResponse> findById(@PathVariable Long id){
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(service.findById(id));
    }

    @PostMapping("/save")
    public ResponseEntity<CategoryResponse> save(@RequestBody CategoryRequest categoryRequest){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.save(categoryRequest));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete (@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
