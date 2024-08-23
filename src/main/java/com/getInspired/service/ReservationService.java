package com.getInspired.service;

import com.getInspired.exception.DatabaseEmptyException;
import com.getInspired.model.Reservation;
import com.getInspired.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;

    public List<Reservation> getAll() {
        List<Reservation> reservations = reservationRepository.findAll();
        if (reservations.isEmpty()) {
            throw new DatabaseEmptyException();
        }
        return reservations;
    }

    public Reservation save(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    public Reservation cancelReservation(Long id){
        Optional<Reservation> reservation = reservationRepository.findById(id);
        reservationRepository.delete(reservation.get());
        return reservation.get();
    }


    public Reservation confirmReservation(Long id) {
        Reservation reservation = reservationRepository.findById(id).get();
        reservation.setIsConfirmed(true);
        return reservationRepository.save(reservation);
    }
}
