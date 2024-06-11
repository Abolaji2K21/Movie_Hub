package com.mavericktube.MaverickHub.Services;

import com.mavericktube.MaverickHub.Models.User;
import com.mavericktube.MaverickHub.Repositories.UserRepository;
import com.mavericktube.MaverickHub.dtos.requests.CreateUserRequest;
import com.mavericktube.MaverickHub.dtos.responds.CreateUserResponse;
import com.mavericktube.MaverickHub.exceptions.UserNotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                                   ModelMapper modelMapper) {

        this.userRepository = userRepository;
        this.modelMapper=modelMapper;
    }

    @Override
    public CreateUserResponse register(CreateUserRequest request) {
        User user = modelMapper.map(request, User.class);
        user = userRepository.save(user);
        var response = modelMapper.map(user, CreateUserResponse.class);
        response.setMessage("user registered successfully");
        return response;
    }

    @Override
    public User getById(Long id) throws UserNotFoundException {
        return userRepository.findById(id)
                .orElseThrow(()-> new UserNotFoundException(
                        String.format("user with id %d not found", id)));
    }
}
