package br.com.movieflix.movieflix.controller;

import br.com.movieflix.movieflix.request.MovieRequest;
import br.com.movieflix.movieflix.response.MovieResponse;
import br.com.movieflix.movieflix.service.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movieflix/movie")
public class MovieController {
    private final MovieService service;

    public MovieController(MovieService service) {
        this.service = service;
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

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete (@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
