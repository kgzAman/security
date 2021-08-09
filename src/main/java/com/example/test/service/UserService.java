package com.example.test.service;


import com.example.test.dto.UserRegisterForm;
import com.example.test.dto.UserResponseDTO;
import com.example.test.entity.User;
import com.example.test.enums.Role;
import com.example.test.exeption.NotFoundException;
import com.example.test.exeption.UserAlreadyRegisteredException;
import com.example.test.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
@AllArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder encoder;
    private final PasswordEncoder passwordEncoder;


    public UserResponseDTO register(UserRegisterForm form) {
        if (repository.existsByEmail(form.getEmail())) {
            throw new UserAlreadyRegisteredException();
        }
        User user
                = User.builder()
                .email(form.getEmail())
                .name(form.getName())
                .password(encoder.encode(form.getPassword()))
                .active(true)
                .build();

        List<User> users= repository.findAll();
        if(users.isEmpty()) {
            user.setRole(Role.ADMIN);
        }
        else {
            user.setRole(Role.USER);
        }

        repository.save(user);

        return UserResponseDTO.from(user);
    }

    public void deleteUser(String email){
        User user = repository.findByEmail(email).orElseThrow(NotFoundException::new);
        repository.delete(user);
    }


    public User getUser(String email){
        return repository.findByEmail(email).orElseThrow(NotFoundException::new);
    }

    public void addAdmin(String email) {
        User user = repository.findByEmail(email).orElseThrow(NotFoundException::new);
        user.setRole(Role.ADMIN);
        repository.save(user);
    }
}
