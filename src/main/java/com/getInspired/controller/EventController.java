package com.getInspired.controller;


import com.getInspired.exception.DatabaseEmptyException;
import com.getInspired.exception.EventNotFoundException;
import com.getInspired.model.Event;
import com.getInspired.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/event")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class EventController {
    private final EventService eventService;

    @PreAuthorize("hasAnyAuthority('ADMIN','MEMBRE')")
    @GetMapping("/getAll")
    public ResponseEntity<?> getAll() {
        try {
            List<Event> events = eventService.getAllEvent();
            return ResponseEntity.ok(events);
        } catch (DatabaseEmptyException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','MEMBRE')")
    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        try {
            Event event = eventService.getByIdEvent(id);
            return ResponseEntity.ok(event);
        } catch (DatabaseEmptyException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody Event event ){
        return ResponseEntity.status(HttpStatus.CREATED).body(eventService.saveEvent(event));
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody Event event ){
        return ResponseEntity.status(HttpStatus.OK).body(eventService.updateEvent(event));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        try {
            Event deletedEvent = eventService.deleteEvent(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(deletedEvent);
        } catch (EventNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}
