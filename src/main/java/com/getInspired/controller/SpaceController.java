package com.getInspired.controller;

import com.getInspired.exception.DatabaseEmptyException;
import com.getInspired.exception.EventNotFoundException;
import com.getInspired.model.Event;
import com.getInspired.model.Space;
import com.getInspired.service.EventService;
import com.getInspired.service.SpaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/space")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class SpaceController {
    private final SpaceService spaceService;
    @PreAuthorize("hasAnyAuthority('ADMIN','MEMBRE')")
    @GetMapping("/getAll")
    public ResponseEntity<?> getAll() {
        try {
            List<Space> spaces = spaceService.getAllSpaces();
            return ResponseEntity.ok(spaces);
        } catch (DatabaseEmptyException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @PreAuthorize("hasAnyAuthority('ADMIN','MEMBRE')")
    @GetMapping("/getById")
    public ResponseEntity<?> getById(Long id){
        try {
            Space space = spaceService.getByIdSpace(id);
            return ResponseEntity.ok(space);
        } catch (DatabaseEmptyException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody Space space ){
        return ResponseEntity.status(HttpStatus.CREATED).body(spaceService.saveSpace(space));
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody Space space ){
        return ResponseEntity.status(HttpStatus.OK).body(spaceService.updateSpace(space));
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        try {
            Space deletedSpace = spaceService.deleteSpace(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(deletedSpace);
        } catch (EventNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/reserve/{id}")
    public ResponseEntity<?> changeEtatToReserved(@PathVariable Long id ){
        return ResponseEntity.status(HttpStatus.OK).body(spaceService.changeEtatToReserved(id));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/notReserve/{id}")
    public ResponseEntity<?> changeEtatToNotReserved(@PathVariable Long id ){
        return ResponseEntity.status(HttpStatus.OK).body(spaceService.changeEtatToNotReserved(id));
    }



}
