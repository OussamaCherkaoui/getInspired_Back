package com.getInspired.controller;

import com.getInspired.enums.Role;
import com.getInspired.model.Admin;
import com.getInspired.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AdminController{

    private final PasswordEncoder passwordEncoder;
    private final AdminService adminService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody Admin admin) {
        admin.setRole(Role.ADMIN);
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        return new ResponseEntity<>(adminService.saveAdmin(admin), HttpStatus.CREATED);
    }
}
