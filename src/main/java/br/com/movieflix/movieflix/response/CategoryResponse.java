package br.com.movieflix.movieflix.response;

import lombok.Builder;

//Parâmetros do construtor de CategoryResponse
@Builder
public record CategoryResponse(Long id, String name) {
}
