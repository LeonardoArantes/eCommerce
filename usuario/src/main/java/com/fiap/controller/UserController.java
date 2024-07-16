package com.fiap.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fiap.controller.request.UserLoginRequest;
import com.fiap.controller.request.UserPasswordRequest;
import com.fiap.dto.UserDTO;
import com.fiap.model.User;
import com.fiap.service.UserService;

@RestController
@RequestMapping("/usuario")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<Page<UserDTO>> findAll(
            @PageableDefault (size=10, page=0, sort="name")Pageable pageable
    )
    {
        Page<UserDTO> usersDTO = userService.findAll(pageable);

        return ResponseEntity.ok(usersDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable Long id){
        UserDTO usersDTO = userService.findById(id);

        return ResponseEntity.ok(usersDTO);
    }

    @PostMapping
    public ResponseEntity<UserDTO> save(@Valid @RequestBody UserDTO userDTO){
        UserDTO savedUsuario = userService.save(userDTO);

        return new ResponseEntity<>(savedUsuario, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> update(@PathVariable Long id, @RequestBody UserDTO userDTO){
        UserDTO updatedUsuario = userService.update(id, userDTO);

        return ResponseEntity.ok(updatedUsuario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        userService.delete(id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginRequest> login(@Valid @RequestBody UserLoginRequest loginRequest) {
        User user = new User(loginRequest.id(), loginRequest.email(), loginRequest.password());

        UserLoginRequest userLoginRequest = userService.login(user.getEmail(), user.getPassword());
        if (userLoginRequest != null) {
            return ResponseEntity.ok(userLoginRequest);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PutMapping("/{id}/novasenha")
    public ResponseEntity<UserPasswordRequest> changePassword(@RequestBody UserPasswordRequest userPasswordRequest){
        UserPasswordRequest updatedUsuario = userService.changePassword(userPasswordRequest);

        return ResponseEntity.ok(updatedUsuario);
    }


}
