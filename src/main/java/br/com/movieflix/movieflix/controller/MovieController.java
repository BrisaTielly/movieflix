package br.com.movieflix.movieflix.controller;

import br.com.movieflix.movieflix.repository.CategoryRepository;
import br.com.movieflix.movieflix.request.MovieRequest;
import br.com.movieflix.movieflix.response.MovieResponse;
import br.com.movieflix.movieflix.service.MovieService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/movieflix/movie")
public class MovieController {
    private final MovieService service;
    private final CategoryRepository categoryRepository;

    public MovieController(MovieService service, CategoryRepository categoryRepository) {
        this.service = service;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/list")
    public ResponseEntity<List<MovieResponse>> findAll(){
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(service.findAll());
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<MovieResponse> findById(@PathVariable Long id){
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(service.findById(id));
    }

    @PostMapping("/save")
    public ResponseEntity<MovieResponse> save(@RequestBody MovieRequest movieRequest){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.save(movieRequest));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<MovieResponse> update(@PathVariable Long id, @RequestBody MovieRequest movieRequest){
        Optional<MovieResponse> update = service.update(id, movieRequest);
        return update.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete (@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
