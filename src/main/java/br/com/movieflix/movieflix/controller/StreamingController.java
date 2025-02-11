package br.com.movieflix.movieflix.controller;

import br.com.movieflix.movieflix.request.StreamingRequest;
import br.com.movieflix.movieflix.response.StreamingResponse;
import br.com.movieflix.movieflix.service.StreamingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movieflix/streaming")
public class StreamingController {
    private final StreamingService service;

    public StreamingController(StreamingService service) {

        this.service = service;
    }

    @GetMapping("/list")
    public ResponseEntity<List<StreamingResponse>> findAll(){
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(service.findAll());
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<StreamingResponse> findById(@PathVariable Long id){
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(service.findById(id));
    }

    @PostMapping("/save")
    public ResponseEntity<StreamingResponse> save(@RequestBody StreamingRequest streamingRequest){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.save(streamingRequest));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete (@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
