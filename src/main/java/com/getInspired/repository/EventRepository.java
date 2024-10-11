package com.getInspired.repository;

import com.getInspired.model.Event;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    @Query(value = "SELECT COUNT(*) FROM Event WHERE date>=CURRENT_DATE",nativeQuery = true)
    Long countEvent();

    List<Event> findAllByOrderByDateDesc();

    List<Event> findAllByDateOrderByDateDesc(LocalDate date);
}
