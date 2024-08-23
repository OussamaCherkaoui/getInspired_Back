package com.getInspired.controller;

import com.getInspired.exception.DatabaseEmptyException;
import com.getInspired.model.Event;
import com.getInspired.model.EventRegistration;
import com.getInspired.service.EventRegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/eventRegistration")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class EventRegistrationController {


    private final EventRegistrationService eventRegistrationService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/getAll")
    public ResponseEntity<?> getAll() {
        try {
            List<EventRegistration> eventRegistrations = eventRegistrationService.getAllRegistration();
            return ResponseEntity.ok(eventRegistrations);
        } catch (DatabaseEmptyException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @PreAuthorize("hasAuthority('MEMBRE')")
    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody EventRegistration eventRegistration ){
        return ResponseEntity.status(HttpStatus.CREATED).body(eventRegistrationService.saveEventRegistration(eventRegistration));
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/confirmRegistration/{id}")
    public ResponseEntity<?> confirmRegistration(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(eventRegistrationService.confirmRegistration(id));
    }

}
