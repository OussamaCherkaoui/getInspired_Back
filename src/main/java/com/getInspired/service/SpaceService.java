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

    public List<Space> getAllSpaces() {
        List<Space> spaces = spaceRepository.findAll();
        if (spaces.isEmpty()) {
            throw new DatabaseEmptyException();
        }
        return spaces;
    }
    public Space saveSpace(Space space) {
        return spaceRepository.save(space);
    }

    public Space updateSpace(Space space) {
        return spaceRepository.save(space);
    }

    public Space getByIdSpace(Long id) throws SpaceNotFoundException {
        return spaceRepository.findById(id).orElseThrow(SpaceNotFoundException::new);
    }
    public Space deleteSpace(Long id) throws SpaceNotFoundException {
        Space space = spaceRepository.findById(id).orElseThrow(SpaceNotFoundException::new);
        spaceRepository.delete(space);
        return space;
    }
    public Space changeEtatToReserved(Long id) {
        Space space = spaceRepository.findById(id).orElseThrow(SpaceNotFoundException::new);
        space.setIsReserved(true);
        return spaceRepository.save(space);
    }

    public Space changeEtatToNotReserved(Long id) {
        Space space = spaceRepository.findById(id).orElseThrow(SpaceNotFoundException::new);
        space.setIsReserved(false);
        return spaceRepository.save(space);
    }
    public Long countFreeSpaceForToday(){
        return spaceRepository.countFreeSpaceForToday();
    }
}
