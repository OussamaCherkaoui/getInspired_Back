package com.getInspired.repository;

import com.getInspired.dto.ReservationDto;
import com.getInspired.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findBySpace_Id(Long id);

    @Query(value = "Select * from reservation r where r.start_time>=current_date",nativeQuery = true)
    List<Reservation> UpcomingReservations();

    @Query(value = "SELECT new com.getInspired.dto.ReservationDto(r.id,r.startTime,r.endTime,r.isConfirmed,r.membre.id,r.space.id,s.name,s.picture) FROM Reservation r " +
            "join r.space s " +
            " where r.membre.id=:id")
    List<ReservationDto> findAllByIdmember(Long id);

    List<Reservation> findByStartTimeBetween(LocalDateTime start, LocalDateTime end);
}
