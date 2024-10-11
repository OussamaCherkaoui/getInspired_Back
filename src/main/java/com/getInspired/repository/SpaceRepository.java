package com.getInspired.repository;

import com.getInspired.model.Event;
import com.getInspired.model.Space;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SpaceRepository extends JpaRepository<Space, Long> {
    @Query(value = "SELECT COUNT(s.id) \n" +
            "FROM space s \n" +
            "WHERE s.id NOT IN (SELECT r.id_space FROM reservation r WHERE r.start_time = CURRENT_DATE AND r.is_confirmed=true);\n",nativeQuery = true)
    Long countFreeSpaceForToday();
}
