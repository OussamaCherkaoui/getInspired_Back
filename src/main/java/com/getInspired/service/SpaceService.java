package com.getInspired.service;

import com.getInspired.exception.DatabaseEmptyException;
import com.getInspired.exception.SpaceNotFoundException;
import com.getInspired.model.Space;
import com.getInspired.repository.SpaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class SpaceService {

    private final SpaceRepository spaceRepository;

    public List<Space> getAll() {
        List<Space> spaces = spaceRepository.findAll();
        if (spaces.isEmpty()) {
            throw new DatabaseEmptyException();
        }
        return spaces;
    }
    public Space save(Space space) {
        return spaceRepository.save(space);
    }

    public Space update(Space space) {
        return spaceRepository.save(space);
    }

    public Space getById(Long id) throws SpaceNotFoundException {
        return spaceRepository.findById(id).orElseThrow(SpaceNotFoundException::new);
    }
    public Space delete(Long id) throws SpaceNotFoundException {
        Space space = spaceRepository.findById(id).orElseThrow(SpaceNotFoundException::new);
        spaceRepository.delete(space);
        return space;
    }
    public Space reserverdEtat(Space space) {
        space.setIsReserved(true);
        return spaceRepository.save(space);
    }
}
