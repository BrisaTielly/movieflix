package br.com.movieflix.movieflix.service;

import br.com.movieflix.movieflix.entity.User;
import br.com.movieflix.movieflix.mapper.UserMapper;
import br.com.movieflix.movieflix.repository.UserRepository;
import br.com.movieflix.movieflix.request.UserRequest;
import br.com.movieflix.movieflix.response.UserResponse;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse save (UserRequest userRequest){
        User user = UserMapper.mapToUser(userRequest);
        user = userRepository.save(user);
        return UserMapper.mapToResponse(user);
    }
}
