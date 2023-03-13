package com.ofc.management.controller;

import com.ofc.management.model.dto.UserRequestDTO;
import com.ofc.management.model.dto.UserResponseDTO;
import com.ofc.management.model.dto.UserUpdateDTO;
import com.ofc.management.service.UserService;
import lombok.RequiredArgsConstructor;
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
    @Secured("ADMIN")
    //TODO secured admin not working
    public ResponseEntity<List<UserResponseDTO>> getUsers() {
        return ResponseEntity.ok(userService.findAll());
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserRequestDTO userRequestDTO) {
        return ResponseEntity.ok(userService.createUser(userRequestDTO));
    }

    @PostMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Integer id, @RequestBody UserUpdateDTO userUpdateDTO) {
        return ResponseEntity.ok(userService.updateUser(id, userUpdateDTO));
    }

    @GetMapping("/musicians")
    public ResponseEntity<List<UserResponseDTO>> getMusicians() {
        return ResponseEntity.ok(userService.findAllMusicians());
    }
}
