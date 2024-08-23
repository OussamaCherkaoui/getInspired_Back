package com.getInspired.repository;

import com.getInspired.model.Event;
import com.getInspired.model.Space;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpaceRepository extends JpaRepository<Space, Long> {

}
