package com.getInspired.controller;

import com.getInspired.config.AuthenticationResponse;
import com.getInspired.exception.UserNotFoundException;
import com.getInspired.model.AuthenticationRequest;
import com.getInspired.model.User;
import com.getInspired.service.UserService;
import com.getInspired.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class UserController {
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> loginUser(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
        );
        final UserDetails userDetails = userService.loadUserByUsername(authenticationRequest.getUsername());
        User user = userService.getUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails,user.getRole());
        return new ResponseEntity<>(new AuthenticationResponse(jwt), HttpStatus.OK);
    }

    @GetMapping("/getIdByUserName/{username}")
    public ResponseEntity<?> getIdByUserName(@PathVariable("username") String username) {
        try {
            User user = userService.getUserByUsername(username);
            return ResponseEntity.ok(user.getId());
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @GetMapping("/getUserByIdUser/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") Long id) {
        try {
            User user = userService.getUserById(id);
            return ResponseEntity.ok(user);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
