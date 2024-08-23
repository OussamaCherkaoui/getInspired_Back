package com.getInspired.controller;

import com.getInspired.exception.DatabaseEmptyException;
import com.getInspired.model.EventRegistration;
import com.getInspired.model.Reservation;
import com.getInspired.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservation")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ReservationController {
    private final ReservationService reservationService;
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/getAll")
    public ResponseEntity<?> getAll() {
        try {
            List<Reservation> reservations = reservationService.getAllReservation();
            return ResponseEntity.ok(reservations);
        } catch (DatabaseEmptyException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @PreAuthorize("hasAuthority('MEMBRE')")
    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody Reservation reservation ){
        return ResponseEntity.status(HttpStatus.CREATED).body(reservationService.saveReservation(reservation));
    }
    @PreAuthorize("hasAuthority('MEMBRE')")
    @DeleteMapping("/cancelReservation/{id}")
    public ResponseEntity<?> cancelReservation(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(reservationService.cancelReservation(id));
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/confirmReservation/{id}")
    public ResponseEntity<?> confirmReservation(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(reservationService.confirmReservation(id));
    }

}
