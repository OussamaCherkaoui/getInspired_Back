package com.getInspired.controller;

import com.getInspired.enums.Role;
import com.getInspired.model.Membre;
import com.getInspired.service.MembreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/membre")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class MembreController{
    private final PasswordEncoder passwordEncoder;
    private final MembreService membreService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody Membre membre) {
        membre.setPassword(passwordEncoder.encode(membre.getPassword()));
        return new ResponseEntity<>(membreService.saveMembre(membre), HttpStatus.CREATED);
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/countRegistredUser")
    public ResponseEntity<?> countRegistredUser() {
        return new ResponseEntity<>(membreService.getCountMembreRegistred(), HttpStatus.OK);
    }
}
