package br.com.movieflix.movieflix.service;

import br.com.movieflix.movieflix.entity.Streaming;
import br.com.movieflix.movieflix.mapper.StreamingMapper;
import br.com.movieflix.movieflix.repository.StreamingRepository;
import br.com.movieflix.movieflix.request.StreamingRequest;
import br.com.movieflix.movieflix.response.StreamingResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StreamingService {
    private final StreamingRepository repository;

    public StreamingService(StreamingRepository repository) {
        this.repository = repository;
    }

    //Tenho que receber um request, transformar em Streaming e transformar em um Response
    public List<StreamingResponse> findAll(){
        return repository.findAll().stream()
                .map(StreamingMapper::mapToResponse)
                .collect(Collectors.toList());
    }

    public StreamingResponse findById(Long id){
        Optional<Streaming> streaming = repository.findById(id);
        return streaming.map(StreamingMapper::mapToResponse).orElse(null);
    }

    public StreamingResponse save(StreamingRequest request) {
        Streaming streaming = StreamingMapper.mapToStreaming(request);
        streaming = repository.save(streaming);
        return StreamingMapper.mapToResponse(streaming);
    }

    public void delete(Long id){
        repository.deleteById(id);
    }

    public List<Streaming> findAllByIds(List<Long> streamings) {
        return repository.findAllById(streamings);
    }
}
