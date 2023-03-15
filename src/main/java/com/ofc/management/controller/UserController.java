package com.ofc.management.controller;

import com.ofc.management.model.dto.*;
import com.ofc.management.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<List<UserResponseDTO>> getUsers() {
        return ResponseEntity.ok(userService.findAll());
    }

    @PostMapping
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserRequestDTO userRequestDTO) {
        return new ResponseEntity<>(userService.createUser(userRequestDTO), HttpStatus.CREATED);
    }

    @PostMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Integer id, @RequestBody UserUpdateDTO userUpdateDTO) {
        return ResponseEntity.ok(userService.updateUser(id, userUpdateDTO));
    }

    @GetMapping("/{id}")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<UserResponseDTO> getUser(@PathVariable Integer id) {
        return ResponseEntity.ok(userService.findUserById(id));
    }

    @DeleteMapping("/{id}")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<String> deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("Usuario eliminado");
    }

    @GetMapping("/musicians")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<List<UserResponseDTO>> getMusicians() {
        return ResponseEntity.ok(userService.findAllMusicians());
    }

    @GetMapping("/profile")
    public ResponseEntity<UserProfileDTO> getProfile(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(userService.getProfile(token.substring(7)));
    }

    @PostMapping("/profile/password")
    public ResponseEntity<String> updatePassword(@RequestHeader("Authorization") String token, @RequestBody ChangePasswordDTO password) {
        userService.updatePassword(token.substring(7), password);
        return ResponseEntity.ok("Contraseña actualizada");
    }

}
