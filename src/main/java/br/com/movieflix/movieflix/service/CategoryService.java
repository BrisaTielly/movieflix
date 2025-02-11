package br.com.movieflix.movieflix.service;

import br.com.movieflix.movieflix.entity.Category;
import br.com.movieflix.movieflix.mapper.CategoryMapper;
import br.com.movieflix.movieflix.repository.CategoryRepository;
import br.com.movieflix.movieflix.request.CategoryRequest;
import br.com.movieflix.movieflix.response.CategoryResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    private final CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    //Tenho que receber um request, transformar em Category e transformar em um Response
    public List<CategoryResponse> findAll(){
       return repository.findAll().stream()
                .map(CategoryMapper::mapToResponse)
                .collect(Collectors.toList());
    }

    public CategoryResponse findById(Long id){
        Optional<Category> category = repository.findById(id);
        return category.map(CategoryMapper::mapToResponse).orElse(null);
    }

    public CategoryResponse save(CategoryRequest request) {
        Category category = CategoryMapper.mapToCategory(request);
        category = repository.save(category);
        return CategoryMapper.mapToResponse(category);
    }

    public void delete(Long id){
        repository.deleteById(id);
    }

    public List<Category> findAllByIds(List<Long> categories) {
        return repository.findAllById(categories);
    }
}
