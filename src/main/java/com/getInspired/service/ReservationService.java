package com.getInspired.service;

import com.getInspired.dto.ReservationDto;
import com.getInspired.exception.DatabaseEmptyException;
import com.getInspired.model.Reservation;
import com.getInspired.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;

    public List<Reservation> getAllReservation() {
        List<Reservation> reservations = reservationRepository.findAll();
        if (reservations.isEmpty()) {
            throw new DatabaseEmptyException();
        }
        return reservations;
    }
    public List<Reservation> getAllReservationByDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(date, formatter);
        LocalDateTime startOfDay = localDate.atStartOfDay();

        LocalDateTime endOfDay = localDate.atTime(23, 59, 59);

        List<Reservation> reservations = reservationRepository.findByStartTimeBetween(startOfDay,endOfDay);
        if (reservations.isEmpty()) {
            throw new DatabaseEmptyException();
        }
        return reservations;
    }
    public List<Reservation> UpcomingReservations() {
        List<Reservation> reservations = reservationRepository.UpcomingReservations();
        if (reservations.isEmpty()) {
            throw new DatabaseEmptyException();
        }
        return reservations;
    }

    public Reservation saveReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    public Reservation cancelReservation(Long id){
        Reservation reservation = reservationRepository.findById(id).get();
        reservation.setIsConfirmed(false);
        return reservationRepository.save(reservation);
    }


    public Reservation confirmReservation(Long id) {
        Reservation reservation = reservationRepository.findById(id).get();
        reservation.setIsConfirmed(true);
        return reservationRepository.save(reservation);
    }

    public List<Reservation> getAllReservationByIdSpace(Long id) {
        List<Reservation> reservations = reservationRepository.findBySpace_Id(id);
        if (reservations.isEmpty()) {
            throw new DatabaseEmptyException();
        }
        return reservations;
    }

    public List<ReservationDto> getAllReservationByIdMember(Long id) {
        List<ReservationDto> reservations = reservationRepository.findAllByIdmember(id);
        if (reservations.isEmpty()) {
            throw new DatabaseEmptyException();
        }
        return reservations;
    }

    public Boolean deleteByid(Long id) {
        Optional<Reservation> reservation = reservationRepository.findById(id);
        if (reservation.isPresent()){
            reservationRepository.delete(reservation.get());
            return true;
        }
        return false;
    }
}
