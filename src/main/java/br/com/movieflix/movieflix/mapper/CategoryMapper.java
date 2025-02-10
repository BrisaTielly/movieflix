package br.com.movieflix.movieflix.mapper;

import br.com.movieflix.movieflix.entity.Category;
import br.com.movieflix.movieflix.request.CategoryRequest;
import br.com.movieflix.movieflix.response.CategoryResponse;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CategoryMapper {
    //Mapeando de Request para Category
    public Category mapToCategory(CategoryRequest categoryRequest){
        return Category.builder()
                .name(categoryRequest.name())
                .build();
    }

    public CategoryResponse mapToResponse(Category category){
        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }
}
