package com.getInspired.repository;

import com.getInspired.dto.EventRegistrationDto;
import com.getInspired.dto.EventRegistrationReadDto;
import com.getInspired.model.EventRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRegistrationRepository extends JpaRepository<EventRegistration, Long> {
    @Query("SELECT new com.getInspired.dto.EventRegistrationReadDto(er.id, er.isConfirmed, u.username, e.name) " +
            "FROM EventRegistration er " +
            "JOIN er.event e " +
            "JOIN er.membre u " +
            "WHERE e.id = :eventId")
    List<EventRegistrationReadDto> findAllByEvent_Id(@Param("eventId") Long id);
}
