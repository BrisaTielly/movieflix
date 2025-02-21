package br.com.movieflix.movieflix.mapper;

import br.com.movieflix.movieflix.entity.User;
import br.com.movieflix.movieflix.request.UserRequest;
import br.com.movieflix.movieflix.response.UserResponse;
import lombok.experimental.UtilityClass;
import org.springframework.stereotype.Component;

@UtilityClass
public class UserMapper {
    public User mapToUser(UserRequest userRequest){
        return User.builder()
                .name(userRequest.name())
                .email(userRequest.email())
                .password(userRequest.password())
                .build();
    }

    public UserResponse mapToResponse(User user){
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }

}
