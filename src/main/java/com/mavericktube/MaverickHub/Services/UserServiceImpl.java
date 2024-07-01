package com.mavericktube.MaverickHub.Services;

import com.mavericktube.MaverickHub.Models.Authority;
import com.mavericktube.MaverickHub.Models.User;
import com.mavericktube.MaverickHub.Repositories.UserRepository;
import com.mavericktube.MaverickHub.dtos.requests.CreateUserRequest;
import com.mavericktube.MaverickHub.dtos.responds.CreateUserResponse;
import com.mavericktube.MaverickHub.exceptions.UserNotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           ModelMapper modelMapper,
                           PasswordEncoder passwordEncoder) {

        this.userRepository = userRepository;
        this.modelMapper=modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public CreateUserResponse register(CreateUserRequest request) {
        User user = modelMapper.map(request, User.class);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setAuthorities(new HashSet<>());
        var authorities   =user.getAuthorities();
        authorities.add(Authority.USER);
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

    @Override
    public User getUserByUsername(String username) throws UserNotFoundException {

        User user = userRepository.findByEmail(username).orElseThrow(()-> new UserNotFoundException("User not found"));
        return user;
    }

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return null;
//    }
}
