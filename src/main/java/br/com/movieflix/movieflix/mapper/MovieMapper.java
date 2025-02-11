package br.com.movieflix.movieflix.mapper;

import br.com.movieflix.movieflix.entity.Category;
import br.com.movieflix.movieflix.entity.Movie;
import br.com.movieflix.movieflix.entity.Streaming;
import br.com.movieflix.movieflix.request.MovieRequest;
import br.com.movieflix.movieflix.request.StreamingRequest;
import br.com.movieflix.movieflix.response.CategoryResponse;
import br.com.movieflix.movieflix.response.MovieResponse;
import br.com.movieflix.movieflix.response.StreamingResponse;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class MovieMapper {

    public Movie mapToMovie(MovieRequest movieRequest) {
        List<Category> categories = movieRequest.categories()
                .stream()
                .map(categoryId -> Category.builder().id(categoryId).build())
                .toList();
        //Criando instâncias temporárias dessas entidades, sem salvar no banco.

        List<Streaming> streamings = movieRequest.streamings()
                .stream()
                .map(streamingId -> Streaming.builder().id(streamingId).build())
                .toList();

        return Movie.builder()
                .title(movieRequest.title())
                .description(movieRequest.description())
                .rating(movieRequest.rating())
                .releaseDate(movieRequest.releaseDate())
                .categories(categories)
                .streamings(streamings)
                .build();
    }

    public MovieResponse mapToResponse(Movie movie){
        List<CategoryResponse> categories = movie.getCategories()
                .stream()
                .map(CategoryMapper::mapToResponse)
                .toList();
        //Criando instâncias temporárias dessas entidades, sem salvar no banco.

        List<StreamingResponse> streamings = movie.getStreamings()
                .stream()
                .map(StreamingMapper::mapToResponse)
                .toList();

        return MovieResponse.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .description(movie.getDescription())
                .releaseDate(movie.getReleaseDate())
                .rating(movie.getRating())
                .categories(categories)
                .streamings(streamings)
                .build();
    }

}
