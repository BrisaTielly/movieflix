package br.com.movieflix.movieflix.repository;

import br.com.movieflix.movieflix.entity.Category;
import br.com.movieflix.movieflix.entity.Movie;
import br.com.movieflix.movieflix.response.MovieResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long>{
    List<Movie> findMovieByCategories(List<Category> categories);
}
