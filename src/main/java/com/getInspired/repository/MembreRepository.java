package com.getInspired.repository;

import com.getInspired.model.Event;
import com.getInspired.model.Membre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MembreRepository  extends JpaRepository<Membre, Long> {
}
