package com.getInspired.controller;

import com.getInspired.dto.ReservationDto;
import com.getInspired.exception.DatabaseEmptyException;
import com.getInspired.mapper.ReservationMapper;
import com.getInspired.model.EventRegistration;
import com.getInspired.model.Reservation;
import com.getInspired.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/reservation")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ReservationController {
    private final ReservationService reservationService;
    private final ReservationMapper reservationMapper;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/getAll")
    public ResponseEntity<?> getAll() {
        try {
            List<Reservation> reservations = reservationService.getAllReservation();
            return ResponseEntity.ok(reservationMapper.toDTO(reservations));
        } catch (DatabaseEmptyException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/getAllByDate/{date}")
    public ResponseEntity<?> getAllByDate(@PathVariable String date) {
        try {
            List<Reservation> reservations = reservationService.getAllReservationByDate(date);
            return ResponseEntity.ok(reservationMapper.toDTO(reservations));
        } catch (DatabaseEmptyException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/getUpcomingReservations")
    public ResponseEntity<?> getUpcomingReservations() {
        try {
            List<Reservation> reservations = reservationService.UpcomingReservations();
            return ResponseEntity.ok(reservationMapper.toDTO(reservations));
        } catch (DatabaseEmptyException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/getAllByIdSpace/{id}")
    public ResponseEntity<?> getAllByIdSpace(@PathVariable Long id) {
        try {
            List<Reservation> reservations = reservationService.getAllReservationByIdSpace(id);
            return ResponseEntity.ok(reservationMapper.toDTO(reservations));
        } catch (DatabaseEmptyException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PreAuthorize("hasAuthority('MEMBRE')")
    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody ReservationDto reservationDto ){
        return ResponseEntity.status(HttpStatus.CREATED).body(reservationMapper.toDTO(reservationService.saveReservation(reservationMapper.toEntity(reservationDto))));
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/cancelReservation/{id}")
    public ResponseEntity<?> cancelReservation(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(reservationMapper.toDTO(reservationService.cancelReservation(id)));
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/confirmReservation/{id}")
    public ResponseEntity<?> confirmReservation(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(reservationMapper.toDTO(reservationService.confirmReservation(id)));
    }

}
