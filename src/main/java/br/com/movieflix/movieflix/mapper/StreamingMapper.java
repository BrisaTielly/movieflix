package br.com.movieflix.movieflix.mapper;

import br.com.movieflix.movieflix.entity.Streaming;
import br.com.movieflix.movieflix.request.StreamingRequest;
import br.com.movieflix.movieflix.response.StreamingResponse;
import lombok.experimental.UtilityClass;

@UtilityClass
public class StreamingMapper {
    public Streaming mapToStreaming(StreamingRequest streamingRequest){
        return Streaming.builder()
                .name(streamingRequest.name())
                .build();
    }

    public StreamingResponse mapToResponse(Streaming streaming){
        return StreamingResponse.builder()
                .id(streaming.getId())
                .name(streaming.getName())
                .build();
    }
}
