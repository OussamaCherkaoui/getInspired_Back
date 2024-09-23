package com.getInspired;

import com.getInspired.exception.DatabaseEmptyException;
import com.getInspired.exception.EventNotFoundException;
import com.getInspired.repository.EventRepository;
import com.getInspired.service.EventService;
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
import static org.mockito.Mockito.when;

@SpringBootTest
public class EventServiceTest {
    @MockBean
    private EventRepository eventRepository;

    @Autowired
    private EventService eventService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testGetAllEventWhenNoEventsFound() {
        when(eventRepository.findAll()).thenReturn(new ArrayList<>());

        assertThrows(DatabaseEmptyException.class, () -> eventService.getAllEvent());
    }

    @Test
    void testDeleteEventWhenEventNotFound() {
        Long eventId = 1L;
        when(eventRepository.findById(eventId)).thenReturn(Optional.empty());

        assertThrows(EventNotFoundException.class, () -> eventService.deleteEvent(eventId));
    }
}
