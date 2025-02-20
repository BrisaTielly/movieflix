package br.com.movieflix.movieflix.service;

import br.com.movieflix.movieflix.entity.Category;
import br.com.movieflix.movieflix.entity.Movie;
import br.com.movieflix.movieflix.entity.Streaming;
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
    private final CategoryService categoryService;
    private final StreamingService streamingService;
    private final MovieRepository movieRepository;


    public MovieService(MovieRepository repository, CategoryService categoryService, StreamingService streamingService, MovieRepository movieRepository) {
        this.repository = repository;
        this.categoryService = categoryService;
        this.streamingService = streamingService;
        this.movieRepository = movieRepository;
    }

    //Tenho que receber um request, transformar em Movie e transformar em um Response
    public List<MovieResponse> findAll() {
        return repository.findAll().stream()
                .map(MovieMapper::mapToResponse)
                .collect(Collectors.toList());
    }

    public MovieResponse findById(Long id) {
        Optional<Movie> movie = repository.findById(id);
        return movie.map(MovieMapper::mapToResponse).orElse(null);
    }

    public MovieResponse save(MovieRequest request) {
        //Retorna uma lista de categorias com a lista de ids que foi passada de argumento
        List<Category> categories = categoryService.findAllByIds(request.categories());
        //Faz o mesmo com os 'streamings'
        List<Streaming> streamings = streamingService.findAllByIds(request.streamings());
        Movie movie = MovieMapper.mapToMovie(request);
        movie.setCategories(categories); //Passando uma lista de categorias
        movie.setStreamings(streamings); //Passando uma lista de Streamings
        movie = repository.save(movie);
        return MovieMapper.mapToResponse(movie);
    }

    public Optional<MovieResponse> update(Long id, MovieRequest attRequest) {
        Optional<Movie> optMovie = repository.findById(id);

        if (optMovie.isPresent()) {
            Movie movie = optMovie.get(); // Mantém o objeto original

            // Atualiza os campos do filme existente
            movie.setTitle(attRequest.title());
            movie.setDescription(attRequest.description());
            movie.setRating(attRequest.rating());
            movie.setReleaseDate(attRequest.releaseDate());

            // Atualiza as categorias
            List<Category> categories = categoryService.findAllByIds(attRequest.categories());
            movie.getCategories().clear();
            movie.getCategories().addAll(categories);

            // Atualiza os streamings
            List<Streaming> streamings = streamingService.findAllByIds(attRequest.streamings());
            movie.getStreamings().clear();
            movie.getStreamings().addAll(streamings);

            // Salva as mudanças no banco de dados
            movie = repository.save(movie);

            return Optional.of(MovieMapper.mapToResponse(movie)); // Converte para resposta
        }

        return Optional.empty(); // Retorna vazio se o filme não existir
    }


    public void delete(Long id) {
        repository.deleteById(id);
    }

}
