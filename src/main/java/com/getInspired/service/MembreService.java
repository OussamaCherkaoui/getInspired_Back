package com.getInspired.service;

import com.getInspired.model.Membre;
import com.getInspired.repository.MembreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MembreService {
    private final MembreRepository membreRepository;

    public Membre saveMembre(Membre membre)
    {
        return membreRepository.save(membre);
    }
}
