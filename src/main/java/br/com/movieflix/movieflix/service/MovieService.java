package br.com.movieflix.movieflix.service;

import br.com.movieflix.movieflix.entity.Category;
import br.com.movieflix.movieflix.entity.Movie;
import br.com.movieflix.movieflix.mapper.CategoryMapper;
import br.com.movieflix.movieflix.mapper.MovieMapper;
import br.com.movieflix.movieflix.repository.CategoryRepository;
import br.com.movieflix.movieflix.repository.MovieRepository;
import br.com.movieflix.movieflix.request.CategoryRequest;
import br.com.movieflix.movieflix.request.MovieRequest;
import br.com.movieflix.movieflix.response.CategoryResponse;
import br.com.movieflix.movieflix.response.MovieResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieService {
    private final MovieRepository repository;

    public MovieService(MovieRepository repository) {
        this.repository = repository;
    }

    //Tenho que receber um request, transformar em Movie e transformar em um Response
    public List<MovieResponse> findAll(){
        return repository.findAll().stream()
                .map(MovieMapper::mapToResponse)
                .collect(Collectors.toList());
    }

    public MovieResponse findById(Long id){
        Optional<Movie> movie = repository.findById(id);
        return movie.map(MovieMapper::mapToResponse).orElse(null);
    }

    public MovieResponse save(MovieRequest request) {
        Movie movie = MovieMapper.mapToMovie(request);
        movie = repository.save(movie);
        return MovieMapper.mapToResponse(movie);
    }

    public void delete(Long id){
        repository.deleteById(id);
    }

}
