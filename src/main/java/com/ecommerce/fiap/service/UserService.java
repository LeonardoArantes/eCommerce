package com.ecommerce.fiap.service;

import com.ecommerce.fiap.controller.exception.ControllerNotFoundException;
import com.ecommerce.fiap.controller.request.UserLoginRequest;
import com.ecommerce.fiap.controller.request.UserPasswordRequest;
import com.ecommerce.fiap.model.User;
import org.springframework.stereotype.Service;
import com.ecommerce.fiap.dto.UserDTO;
import com.ecommerce.fiap.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Page<UserDTO> findAll(Pageable pageable) {
        Page<User> users = userRepository.findAll(pageable);

        return users.map(this::toDTO);
    }

    public UserDTO findById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ControllerNotFoundException("Usuário não encontrado"));
        return toDTO(user);
    }

    public UserDTO save(UserDTO userDTO) {
        User user = toEntity(userDTO);
        user = userRepository.save(user);

        return toDTO(user);
    }

    public UserLoginRequest login(String email, String password) {
        try {
            User user = userRepository.findByEmail(email);
            if (user != null && user.getPassword().equals(password)) {
                return new UserLoginRequest(user.getId(), user.getName(), user.getEmail());
            } else {
                throw new ControllerNotFoundException("Usuário não encontrado");
            }
        } catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Usuário não encontrado");
        }
    }

    public UserPasswordRequest changePassword(UserPasswordRequest userPasswordRequest) {
        try {
            User user = userRepository.findByEmail(userPasswordRequest.email());
            if (user != null && user.getPassword().equals(userPasswordRequest.password())) {
                user.setPassword(userPasswordRequest.newPassword());
                user = userRepository.save(user);
                return new UserPasswordRequest(user.getId(), user.getEmail(), user.getPassword(),"");
            } else {
                throw new ControllerNotFoundException("Usuário não encontrado");
            }
        } catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Usuário não encontrado");
        }
    }

    public UserDTO update(Long id, UserDTO UserDTO) {
        try {
            User user = userRepository.getReferenceById(id);

            user.setType(UserDTO.type());

            if (UserDTO.name() != null)
                 user.setName(UserDTO.name());

            if (UserDTO.email() != null)
                user.setEmail(UserDTO.email());

            if (UserDTO.phone() != null)
                user.setPhone(UserDTO.phone());

            if (UserDTO.photo() != null)
                user.setPhoto(UserDTO.photo());

            if (UserDTO.birthday() != null)
                user.setBirthday(UserDTO.birthday());

            if (UserDTO.password() != null)
                user.setPassword(UserDTO.password());

            user.setUpdatedAt(LocalDateTime.now());
            user = userRepository.save(user);
            return toDTO(user);

        } catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Usuário não encontrado");
        }
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    private UserDTO toDTO(User user) {
        return new UserDTO(
                user.getId(),
                user.getType(),
                user.getName(),
                user.getEmail(),
                user.getPhone(),
                user.getPhoto(),
                user.getBirthday(),
                user.getPassword(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }

    private User toEntity(UserDTO userDTO) {
        return new User(
                userDTO.id(),
                userDTO.type(),
                userDTO.name(),
                userDTO.email(),
                userDTO.phone(),
                userDTO.photo(),
                userDTO.birthday(),
                userDTO.password(),
                userDTO.createdAt(),
                userDTO.updatedAt()
        );
    }
}


