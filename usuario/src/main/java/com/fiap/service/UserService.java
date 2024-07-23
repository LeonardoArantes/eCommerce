package com.fiap.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fiap.controller.exception.ControllerNotFoundException;
import com.fiap.controller.request.UserPasswordRequest;
import com.fiap.dto.UserDTO;
import com.fiap.model.User;
import com.fiap.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

//    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Page<UserDTO> findAll(Pageable pageable) {
        Page<User> users = userRepository.findAll(pageable);

        return users.map(this::toDTO);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return (UserDetails) userRepository.findByEmail(username);
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

    public UserPasswordRequest changePassword(UserPasswordRequest userPasswordRequest) {
        try {
            User user = (User) userRepository.findByEmail(userPasswordRequest.email());
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

            if (UserDTO.login() != null)
                user.setLogin(UserDTO.login());

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
                user.getLogin(),
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
                userDTO.login(),
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


