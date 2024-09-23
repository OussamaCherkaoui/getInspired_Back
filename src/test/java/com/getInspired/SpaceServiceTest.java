package com.getInspired;

import com.getInspired.exception.DatabaseEmptyException;
import com.getInspired.model.Space;
import com.getInspired.repository.SpaceRepository;
import com.getInspired.service.SpaceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest
public class SpaceServiceTest {
    @MockBean
    private SpaceRepository spaceRepository;

    @Autowired
    private SpaceService spaceService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testGetAllSpacesWhenNoSpacesFound() {
        when(spaceRepository.findAll()).thenReturn(new ArrayList<>());

        assertThrows(DatabaseEmptyException.class, () -> spaceService.getAllSpaces());
    }

    @Test
    void testChangeEtatToReserved() {
        Long spaceId = 1L;
        Space space = new Space();
        space.setId(spaceId);
        space.setIsReserved(false);

        when(spaceRepository.findById(spaceId)).thenReturn(Optional.of(space));
        when(spaceRepository.save(space)).thenReturn(space);

        Space updatedSpace = spaceService.changeEtatToReserved(spaceId);
        assertTrue(updatedSpace.getIsReserved());
    }
}
