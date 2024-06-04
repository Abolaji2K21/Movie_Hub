package com.mavericktube.MaverickHub.Services;

import com.mavericktube.MaverickHub.Models.User;
import com.mavericktube.MaverickHub.Repositories.UserRepository;
import com.mavericktube.MaverickHub.dtos.requests.CreateUserRequest;
import com.mavericktube.MaverickHub.dtos.responds.CreateUserResponse;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{


    private final UserRepository userRepository;
    private final ModelMapper modelMapper;


    @Override
    public CreateUserResponse register(CreateUserRequest request) {
//        ModelMapper modelMapper = new ModelMapper();
        User user = modelMapper.map(request, User.class);
        user = userRepository.save(user);
        var response = modelMapper.map(user,CreateUserResponse.class);
        response.setMessage("User  Registered Successfully");
        return response;
    }


    @Override
    public User getById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

}
