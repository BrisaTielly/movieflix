package br.com.movieflix.movieflix.request;

import lombok.Builder;

@Builder
public record StreamingRequest(String name) {
}
