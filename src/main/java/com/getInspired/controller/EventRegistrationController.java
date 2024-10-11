package com.getInspired.controller;

import com.getInspired.dto.EventRegistrationDto;
import com.getInspired.dto.EventRegistrationReadDto;
import com.getInspired.exception.DatabaseEmptyException;
import com.getInspired.mapper.EventMapper;
import com.getInspired.mapper.EventRegistrationMapper;
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
    private final EventRegistrationMapper eventRegistrationMapper;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/getAll")
    public ResponseEntity<?> getAll() {
        try {
            List<EventRegistration> eventRegistrations = eventRegistrationService.getAllRegistration();
            return ResponseEntity.ok(eventRegistrationMapper.toDTO(eventRegistrations));
        } catch (DatabaseEmptyException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/getAllEventRegistrationByIdEvent/{id}")
    public ResponseEntity<?> getAllEventRegistrationByIdEvent(@PathVariable Long id) {
        try {
            List<EventRegistrationReadDto> eventRegistrations = eventRegistrationService.getAllEventRegistrationByIdEvent(id);
            return ResponseEntity.ok(eventRegistrations);
        } catch (DatabaseEmptyException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PreAuthorize("hasAuthority('MEMBRE')")
    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody EventRegistrationDto eventRegistrationDto ){
        return ResponseEntity.status(HttpStatus.CREATED).body(eventRegistrationMapper.toDTO(eventRegistrationService.saveEventRegistration(eventRegistrationMapper.toEntity(eventRegistrationDto))));
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/confirmRegistration/{id}")
    public ResponseEntity<?> confirmRegistration(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(eventRegistrationMapper.toDTO(eventRegistrationService.confirmRegistration(id)));
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/cancelRegistration/{id}")
    public ResponseEntity<?> cancelRegistration(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(eventRegistrationMapper.toDTO(eventRegistrationService.cancelRegistration(id)));
    }
}
