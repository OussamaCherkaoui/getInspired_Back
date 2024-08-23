package com.getInspired.service;

import com.getInspired.exception.DatabaseEmptyException;
import com.getInspired.exception.EventNotFoundException;
import com.getInspired.model.Event;
import com.getInspired.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;

    public List<Event> getAll() {
        List<Event> events = eventRepository.findAll();
        if (events.isEmpty()) {
            throw new DatabaseEmptyException();
        }
        return events;
    }
    public Event save(Event event) {
        return eventRepository.save(event);
    }

    public Event update(Event event) {
        return eventRepository.save(event);
    }

    public Event getById(Long id) throws EventNotFoundException {
        return eventRepository.findById(id).orElseThrow(EventNotFoundException::new);
    }
    public Event delete(Long id) throws EventNotFoundException {
        Event event = eventRepository.findById(id).orElseThrow(EventNotFoundException::new);
        eventRepository.delete(event);
        return event;
    }

}
